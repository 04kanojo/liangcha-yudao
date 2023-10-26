package com.liangcha.framework.log.aspect;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.google.common.collect.Maps;
import com.liangcha.common.pojo.CommonResult;
import com.liangcha.common.utils.ServletUtils;
import com.liangcha.common.utils.TracerUtils;
import com.liangcha.framework.log.annotation.Log;
import com.liangcha.framework.log.enums.OperateTypeEnum;
import com.liangcha.framework.log.service.OperateLogFrameworkService;
import com.liangcha.system.log.domain.OperateLogDO;
import com.liangcha.system.user.enums.UserTypeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static com.liangcha.common.enums.ErrorCodeEnum.SUCCESS;
import static com.liangcha.common.enums.ErrorCodeEnum.SYSTEM_ERROR;
import static com.liangcha.framework.security.utils.SecurityFrameworkUtils.getLoginUserId;
import static com.liangcha.framework.security.utils.SecurityFrameworkUtils.getUserType;

/**
 * @author 凉茶
 */
@Aspect
@Component
public class OperateLogAspect {

    @Resource
    private OperateLogFrameworkService logService;


    /**
     * 获取方法上注解
     */
    @SuppressWarnings("SameParameterValue")
    private static <T extends Annotation> T getMethodAnnotation(ProceedingJoinPoint joinPoint
            , Class<T> annotationClass) {
        return ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(annotationClass);
    }

    /**
     * 获取类上注解
     */
    @SuppressWarnings("SameParameterValue")
    private static <T extends Annotation> T getClassAnnotation(ProceedingJoinPoint joinPoint
            , Class<T> annotationClass) {
        return ((MethodSignature) joinPoint.getSignature()).getMethod().getDeclaringClass().getAnnotation(annotationClass);
    }

    /**
     * 获取请求方法
     */
    private static RequestMethod getFirstMatchRequestMethod(RequestMethod[] requestMethods) {
        if (ArrayUtil.isEmpty(requestMethods)) {
            return null;
        }
        //获取请求方法类型
        RequestMethod result = getFirstLogRequestMethod(requestMethods);
        if (result != null) {
            return result;
        }
        // 兜底，获得第一个
        return requestMethods[0];
    }

    /**
     * 获取RequestMapping子类(PostMapping,GetMapping,DeleteMapping)的集合
     */
    private static RequestMethod[] getRequestMethod(ProceedingJoinPoint joinPoint) {
        // 使用 Spring 的工具类，可以处理 @RequestMapping 别名注解(@PostMapping,@GetMapping,@DeleteMapping)
        RequestMapping requestMapping = AnnotationUtils.getAnnotation(((MethodSignature) joinPoint.getSignature()).getMethod(), RequestMapping.class);
        return requestMapping != null ? requestMapping.method() : new RequestMethod[]{};
    }

    /**
     * 获取数组请求方法
     * <p>
     * orElse: 如果findFirst未获取到,给一个备用值
     */
    private static RequestMethod getFirstLogRequestMethod(RequestMethod[] requestMethods) {
        //优先匹配post,put,delete
        RequestMethod requestMethod = Arrays.stream(requestMethods).filter(rm -> rm == RequestMethod.POST || rm == RequestMethod.PUT || rm == RequestMethod.DELETE).findFirst().orElse(null);
        if (requestMethod != null) {
            return requestMethod;
        }
        // 次要匹配get
        return Arrays.stream(requestMethods).filter(rm -> rm == RequestMethod.GET).findFirst().orElse(null);
    }

    /**
     * 将对应方法注解转换为枚举类
     */
    private static OperateTypeEnum convertOperateLogType(RequestMethod requestMethod) {
        if (requestMethod == null) {
            return null;
        }
        switch (requestMethod) {
            case GET:
                return OperateTypeEnum.GET;
            case POST:
                return OperateTypeEnum.CREATE;
            case PUT:
                return OperateTypeEnum.UPDATE;
            case DELETE:
                return OperateTypeEnum.DELETE;
            default:
                return OperateTypeEnum.OTHER;
        }
    }

    /**
     * 填充请求字段
     */
    private static void fillRequestFields(OperateLogDO operateLogObj) {
        // 获得 Request 对象
        HttpServletRequest request = ServletUtils.getRequest();
        if (request == null) {
            return;
        }
        // 补全请求信息
        operateLogObj.setRequestMethod(request.getMethod());
        operateLogObj.setRequestUrl(request.getRequestURI());
        operateLogObj.setUserIp(ServletUtils.getClientIP(request));
        operateLogObj.setUserAgent(ServletUtils.getUserAgent(request));
    }

    /**
     * 填充方法字段
     */
    private static void fillMethodFields(OperateLogDO operateLogObj,
                                         ProceedingJoinPoint joinPoint,
                                         Log log, LocalDateTime startTime,
                                         Object result, Throwable exception) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        operateLogObj.setJavaMethod(methodSignature.toString());

        if (log.logArgs()) {
            operateLogObj.setJavaMethodArgs(getMethodArgs(joinPoint));
            operateLogObj.setResultData(getResultData(result));
        }
        operateLogObj.setDuration((int) (LocalDateTimeUtil.between(startTime, LocalDateTime.now()).toMillis()));

        // （正常）处理 resultCode 和 resultMsg 字段
        if (result instanceof CommonResult) {
            CommonResult<?> commonResult = (CommonResult<?>) result;
            operateLogObj.setResultCode(commonResult.getCode());
            operateLogObj.setResultMsg(commonResult.getMsg());
        } else {
            operateLogObj.setResultCode(SUCCESS.getCode());
        }

        // （异常）处理 resultCode 和 resultMsg 字段
        if (exception != null) {
            operateLogObj.setResultCode(SYSTEM_ERROR.getCode());
            operateLogObj.setResultMsg(ExceptionUtil.getRootCauseMessage(exception));
        }
    }

    /**
     * 填充模块字段
     */
    private static void fillModuleFields(OperateLogDO operateLog, ProceedingJoinPoint joinPoint, Log log) {
        // 优先读取log注解
        operateLog.setModule(log.module());
        // 如果没读取到,则读取controller类的Api注解内容
        if (StrUtil.isEmpty(operateLog.getModule())) {
            Api api = getClassAnnotation(joinPoint, Api.class);
            if (api != null && StrUtil.isNotEmpty(api.value())) {
                operateLog.setModule(api.value());
            }
        }

        // 优先读取log注解
        operateLog.setName(log.name());
        // 如果没读取到,则读取controller类的ApiOperation注解内容
        if (StrUtil.isEmpty(operateLog.getName())) {
            ApiOperation apiOperation = getMethodAnnotation(joinPoint, ApiOperation.class);
            if (apiOperation != null && StrUtil.isNotEmpty(apiOperation.value())) {
                operateLog.setName(apiOperation.value());
            }
        }

        // 优先读取log注解
        if (ArrayUtil.isNotEmpty(log.type())) {
            operateLog.setType(log.type()[0].getType());
        }
        // 如果没读取到,则读取controller类的注解内容
        if (operateLog.getType() == null) {
            RequestMethod[] requestMethods = getRequestMethod(joinPoint);
            RequestMethod requestMethod = getFirstMatchRequestMethod(requestMethods);
            OperateTypeEnum operateLogType = convertOperateLogType(requestMethod);
            operateLog.setType(operateLogType != null ? operateLogType.getType() : null);
        }
    }

    /**
     * 获取方法参数
     */
    private static String getMethodArgs(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String[] argNames = methodSignature.getParameterNames();
        Object[] argValues = joinPoint.getArgs();
        // 拼接参数
        Map<String, Object> args = Maps.newHashMapWithExpectedSize(argValues.length);
        for (int i = 0; i < argNames.length; i++) {
            String argName = argNames[i];
            Object argValue = argValues[i];
            // 被忽略时，标记为 ignore 字符串，避免和 null 混在一起
            args.put(argName, !isIgnoreArgs(argValue) ? argValue : "[ignore]");
        }
        return JSON.toJSONString(args);
    }

    private static String getResultData(Object result) {
        //TODO 提升：结果脱敏和忽略
        if (result instanceof CommonResult) {
            result = ((CommonResult<?>) result).getData();
        }
        return JSON.toJSONString(result);
    }

    /**
     * 忽略部分类
     */
    private static boolean isIgnoreArgs(Object object) {
        //TODO 没去深度解析,等遇到这几个类的时候再debug试试,现在先用着
        Class<?> clazz = object.getClass();
        // 处理数组的情况
        if (clazz.isArray()) {
            return IntStream.range(0, Array.getLength(object))
                    .anyMatch(index -> isIgnoreArgs(Array.get(object, index)));
        }
        // 递归，处理数组、Collection、Map 的情况
        if (Collection.class.isAssignableFrom(clazz)) {
            return ((Collection<?>) object).stream()
                    .anyMatch((Predicate<Object>) OperateLogAspect::isIgnoreArgs);
        }
        if (Map.class.isAssignableFrom(clazz)) {
            return isIgnoreArgs(((Map<?, ?>) object).values());
        }
        // obj
        return object instanceof MultipartFile
                || object instanceof HttpServletRequest
                || object instanceof HttpServletResponse
                || object instanceof BindingResult;
    }

    /**
     * 核心环绕
     */
    @Around(value = "@annotation(com.liangcha.framework.log.annotation.Log)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 目前，只有管理员，才记录操作日志！所以非管理员，直接调用，不进行记录
        Integer userType = getUserType();
        if (userType != null && !userType.equals(UserTypeEnum.ADMIN.getCode())) {
            return joinPoint.proceed();
        }

        //获取方法注解
        Log log = getMethodAnnotation(joinPoint, Log.class);
        // 记录开始时间
        LocalDateTime startTime = LocalDateTime.now();
        try {
            // 执行原有方法
            Object result = joinPoint.proceed();
            // 记录正常执行时的操作日志
            log(joinPoint, log, startTime, result, null);
            return result;
        } catch (Throwable exception) {
            log(joinPoint, log, startTime, null, exception);
            throw exception;
        }
    }

    /**
     * 记录日志
     */
    private void log(ProceedingJoinPoint joinPoint, Log log, LocalDateTime startTime, Object result, Throwable exception) {
        // 判断不记录的情况
        if (!log.enable()) {
            return;
        }
        OperateLogDO operateLog = new OperateLogDO();
        // 补全通用字段
        operateLog.setTraceId(TracerUtils.getTraceId());
        operateLog.setStartTime(startTime);
        // 补充用户信息
        operateLog.setUserId(getLoginUserId());
        operateLog.setUserType(getUserType());
        // 补全模块信息
        fillModuleFields(operateLog, joinPoint, log);
        // 补全请求信息
        fillRequestFields(operateLog);
        // 补全方法信息
        fillMethodFields(operateLog, joinPoint, log, startTime, result, exception);
        // 异步记录日志
        logService.createOperateLog(operateLog);
    }
}
