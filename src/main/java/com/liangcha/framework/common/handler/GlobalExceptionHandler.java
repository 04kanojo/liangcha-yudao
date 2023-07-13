package com.liangcha.framework.common.handler;

import com.liangcha.framework.common.exception.ErrorCodeEnum;
import com.liangcha.framework.common.exception.ServiceException;
import com.liangcha.framework.common.pojo.CommonResult;
import com.liangcha.framework.common.util.WebFrameworkUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 全局异常处理器
 *
 * @author 凉茶
 */
@Slf4j
@AllArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理 SpringMVC 请求参数缺失
     * 处理 SpringMVC 请求参数类型错误
     * 处理 SpringMVC 参数校验不正确
     * <p>
     * 例如说，接口上设置了 @RequestParam("xx") 参数为 Integer，结果传递 xx 参数类型为 String
     * 例如说，接口上设置了 @RequestParam("xx") 参数，结果并未传递 xx 参数
     * 例如说，接口上设置了 @Valid，参数不允许为空，但是参数为空，
     */
    @ExceptionHandler({MissingServletRequestParameterException.class, MethodArgumentTypeMismatchException.class, MethodArgumentNotValidException.class})
    public CommonResult<?> missingServletRequestParameterExceptionHandler(MissingServletRequestParameterException ex) {
        log.error("[missingServletRequestParameterExceptionHandler]", ex);
        return CommonResult.error(ErrorCodeEnum.ERR_REQUEST);
    }


    /**
     * 处理 SpringMVC 请求地址不存在
     * <p>
     * 注意，它需要设置如下两个配置项：
     * 1. spring.mvc.throw-exception-if-no-handler-found 为 true
     * 2. spring.mvc.static-path-pattern 为 /statics/**
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public CommonResult<?> noHandlerFoundExceptionHandler(NoHandlerFoundException ex) {
        log.error("[noHandlerFoundExceptionHandler]", ex);
        return CommonResult.error(ErrorCodeEnum.NOT_FOUND);
    }

    /**
     * 处理 SpringMVC 请求方法不正确
     * <p>
     * 例如说，A 接口的方法为 GET 方式，结果请求方法为 POST 方式，导致不匹配
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public CommonResult<?> httpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException ex) {
        log.error("[httpRequestMethodNotSupportedExceptionHandler]", ex);
        return CommonResult.error(ErrorCodeEnum.METHOD_NOT_ALLOWED);
    }


    /**
     * 处理 Spring Security 权限不足的异常
     * <p>
     * 来源是，使用 @PreAuthorize 注解，AOP 进行权限拦截
     */
    @ExceptionHandler(value = AccessDeniedException.class)
    public CommonResult<?> accessDeniedExceptionHandler(HttpServletRequest req, AccessDeniedException ex) {
        log.error("[accessDeniedExceptionHandler][userId({}) 无法访问 url({})]", WebFrameworkUtils.getLoginUserId(req), req.getRequestURL(), ex);
        return CommonResult.error(ErrorCodeEnum.SMALL_AUTHORITY);
    }

    /**
     * 处理业务异常 ServiceException
     */
    @ExceptionHandler(value = ServiceException.class)
    public CommonResult<?> serviceExceptionHandler(ServiceException ex) {
        log.error("[serviceExceptionHandler]", ex);
        return CommonResult.error(ex.getErrorCodeEnum());
    }

    /**
     * 处理token过滤器异常
     */
    public CommonResult<?> tokenFilterExceptionHandler(Throwable ex) {
        log.error("token解析失败", ex);
        return CommonResult.error(ErrorCodeEnum.TOKEN_ERR);
    }

    /**
     * 处理系统异常，兜底处理所有的一切
     */
    @ExceptionHandler(value = IOException.class)
    public CommonResult<?> IOExceptionHandler(IOException ex) {
        log.error("[IOException]", ex);
        return CommonResult.error(ErrorCodeEnum.IO_ERR);
    }

    /**
     * 处理系统异常，兜底处理所有的一切
     */
    @ExceptionHandler(value = Exception.class)
    public CommonResult<?> defaultExceptionHandler(Throwable ex) {
        log.error("[Exception]", ex);
        //TODO 将未知异常存入数据库或者保存到本地
        return CommonResult.error(ErrorCodeEnum.UNKNOWN);
    }

}
