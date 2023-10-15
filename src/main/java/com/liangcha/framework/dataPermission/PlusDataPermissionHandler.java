package com.liangcha.framework.dataPermission;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ConcurrentHashSet;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.liangcha.framework.common.enums.UserTypeEnum;
import com.liangcha.framework.permission.service.PermissionService;
import com.liangcha.framework.permission.service.RoleService;
import com.liangcha.framework.security.pojo.LoginUser;
import com.liangcha.system.domain.permission.RoleDO;
import net.sf.jsqlparser.expression.Expression;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.BeanResolver;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static com.liangcha.framework.common.enums.ErrorCodeEnum.*;
import static com.liangcha.framework.common.utils.ServiceExceptionUtil.exception;
import static com.liangcha.framework.security.utils.SecurityFrameworkUtils.getLoginUser;

/**
 * 数据权限过滤
 *
 * @author Lion Li
 * @version 3.5.0
 */
public class PlusDataPermissionHandler {

    /**
     * 方法或类(名称) 与 注解的映射关系缓存
     */
    private final Map<String, DataPermission> dataPermissionCacheMap = new ConcurrentHashMap<>();

    /**
     * 无效注解方法缓存用于快速返回
     */
    private final Set<String> invalidCacheSet = new ConcurrentHashSet<>();

    /**
     * spel 解析器
     */
    private final ExpressionParser parser = new SpelExpressionParser();
    private final ParserContext parserContext = new TemplateParserContext();

    /**
     * bean解析器 用于处理 spel 表达式中对 bean 的调用
     * <p>
     * 此处用的是hutool,ruoyi用的spring自带,如有报错更改包
     */
    private final BeanResolver beanResolver = new BeanFactoryResolver(SpringUtil.getBeanFactory());

    @Resource
    private RoleService roleService;

    @Resource
    private PermissionService permissionService;

    /**
     * @param where             where子句
     * @param mappedStatementId 方法路径,格式：类名.方法名
     * @param isSelect          是否查询语句
     * @return 操作过后的where子句
     */
    public Expression getSqlSegment(Expression where, String mappedStatementId, boolean isSelect) {
        //寻找执行sql语句方法上和类上的注解
        DataPermission dataPermission = findAnnotation(mappedStatementId);
        //如果无数据权限注解,将其添加入缓存,加快访问速度
        if (ArrayUtil.isEmpty(dataPermission)) {
            invalidCacheSet.add(mappedStatementId);
            return where;
        }

        LoginUser loginUser = getLoginUser();
        //loginUser不可能为空,因为必须先登录了才能进入这里,除非测试(强迫症下面报警告才写)
        //TODO 是否能被全局异常捕获还不清楚
        ArrayList<RoleDO> roles = new ArrayList<>();
        roles.add(new RoleDO().setDataScope(3));
        roles.add(new RoleDO().setDataScope(2));

        loginUser = new LoginUser()
                .setUserType(UserTypeEnum.ADMIN.getCode())
                .setId(100L)
                .setRoles(roles);
        if (loginUser == null) {
            throw exception(NO_LOGIN);
        }

        String dataFilterSql = buildDataFilter(dataPermission, loginUser.getRoles(), isSelect);
        System.out.println(dataFilterSql);
//        if (StrUtil.isBlank(dataFilterSql)) {
//            return where;
//        }
//
//        try {
//            Expression expression = CCJSqlParserUtil.parseExpression(dataFilterSql);
//            // 数据权限使用单独的括号 防止与其他条件冲突
//            Parenthesis parenthesis = new Parenthesis(expression);
//            if (ObjectUtil.isNotNull(where)) {
//                return new AndExpression(where, parenthesis);
//            } else {
//                return parenthesis;
//            }
//        } catch (JSQLParserException e) {
//            throw new ServiceException("数据权限解析异常 => " + e.getMessage());
//        }
        return null;
    }

    /**
     * 构造数据过滤sql
     */
    private String buildDataFilter(DataPermission dataPermission, List<RoleDO> roles, boolean isSelect) {
        // 更新或删除需满足所有条件
        String joinStr = isSelect ? " OR " : " AND ";
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setBeanResolver(beanResolver);

        Set<String> conditions = new HashSet<>();
        for (RoleDO role : roles) {
            // 获取角色权限枚举类
            DataScopeTypeEnum type = DataScopeTypeEnum.findCode(role.getDataScope().toString());
            if (ObjectUtil.isNull(type)) {
                throw exception(DATA_SCOPE_NOT_EXISTS);
            }
            // 全部数据权限直接返回
            if (type == DataScopeTypeEnum.ALL) {
                return "";
            }

            boolean isSuccess = false;
            String[] keys = dataPermission.key();
            String[] values = dataPermission.value();
            if (keys.length != values.length) {
                throw exception(DATA_SCOPE_KEY_VALUE_ERR);
            }
            // 包含key变量,才处理
            if (StrUtil.containsAny(type.getSqlTemplate(),
                    Arrays.stream(keys).map(key -> "#" + key).toArray(String[]::new))) {
                // 设置注解变量 key 为表达式变量 value 为变量值
                for (int i = 0; i < keys.length; i++) {
                    context.setVariable(keys[i], values[i]);
                }

                // 解析sql模板并填充
                org.springframework.expression.Expression expression = parser.parseExpression(type.getSqlTemplate(), parserContext);
                System.out.println(expression);
                String sql = parser.parseExpression(type.getSqlTemplate(), parserContext).getValue(context, String.class);
                conditions.add(joinStr + sql);
                isSuccess = true;
            }

            // 未处理成功则填充兜底方案
            if (!isSuccess && StrUtil.isNotBlank(type.getElseSql())) {
                conditions.add(joinStr + type.getElseSql());
            }
        }

        if (CollUtil.isNotEmpty(conditions)) {
            String sql = conditions.stream().filter(Objects::nonNull).collect(Collectors.joining(""));
            return sql.substring(joinStr.length());
        }
        //conditions不会为空,走不到这来
        return "";
    }

    /**
     * @param mappedStatementId 格式: 类路径.方法名
     */
    private DataPermission findAnnotation(String mappedStatementId) {
        StringBuilder sb = new StringBuilder(mappedStatementId);
        int index = sb.lastIndexOf(".");
        //获取类路径
        String clazzName = sb.substring(0, index);
        //获取方法名
        String methodName = sb.substring(index + 1, sb.length());
        Class<?> clazz = ClassUtil.loadClass(clazzName);
        //找到指定方法名(可能方法是重载所以得出来集合)
        List<Method> methods = Arrays.stream(ClassUtil.getDeclaredMethods(clazz)).filter(method -> method.getName().equals(methodName)).collect(Collectors.toList());

        DataPermission dataPermission;
        // 获取方法注解
        for (Method method : methods) {
            //优先从缓存取
            dataPermission = dataPermissionCacheMap.get(mappedStatementId);
            if (ObjectUtil.isNotNull(dataPermission)) {
                return dataPermission;
            }

            //查找有注解的方法
            if (AnnotationUtil.hasAnnotation(method, DataPermission.class)) {
                dataPermission = AnnotationUtil.getAnnotation(method, DataPermission.class);
                dataPermissionCacheMap.put(mappedStatementId, dataPermission);
                return dataPermission;
            }
        }

        //当方法上没有注解,查看类上是否有注解
        // 获取类注解
        if (AnnotationUtil.hasAnnotation(clazz, DataPermission.class)) {
            dataPermission = AnnotationUtil.getAnnotation(clazz, DataPermission.class);
            dataPermissionCacheMap.put(clazzName, dataPermission);
            return dataPermission;
        }

        //如果类上方法上都没有注解,返回空对象
        return null;
    }

    /**
     * 是否为无效方法 无数据权限
     */
    public boolean isInvalid(String mappedStatementId) {
        return invalidCacheSet.contains(mappedStatementId);
    }
}
