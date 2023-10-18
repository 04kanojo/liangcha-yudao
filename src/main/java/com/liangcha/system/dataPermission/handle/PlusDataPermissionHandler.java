package com.liangcha.system.dataPermission.handle;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.collection.ConcurrentHashSet;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.liangcha.framework.security.pojo.LoginUser;
import com.liangcha.system.dataPermission.annotation.DataPermission;
import com.liangcha.system.dataPermission.enums.DataScopeTypeEnum;
import com.liangcha.system.permission.domain.RoleDO;
import com.liangcha.system.user.enums.UserTypeEnum;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Parenthesis;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.BeanResolver;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static com.liangcha.common.enums.ErrorCodeEnum.*;
import static com.liangcha.common.utils.ServiceExceptionUtil.exception;
import static com.liangcha.framework.security.utils.SecurityFrameworkUtils.getLoginUser;

/**
 * 数据权限过滤
 *
 * @author 凉茶
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
     */
    private final BeanResolver beanResolver = new BeanFactoryResolver(SpringUtil.getBeanFactory());

    private static LoginUser test() {
        ArrayList<RoleDO> roles = new ArrayList<>();
//        roles.add(new RoleDO().setDataScope(2));
        roles.add(new RoleDO().setDataScope(4));

        return new LoginUser()
                .setUserType(UserTypeEnum.ADMIN.getCode())
                .setUserId(100L)
                .setRoles(roles)
                .setDeptId(100L);
    }

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
        loginUser = test();

        String dataFilterSql = buildDataFilter(dataPermission, loginUser, isSelect);
        if (StrUtil.isBlank(dataFilterSql)) {
            return where;
        }

        try {
            Expression expression = CCJSqlParserUtil.parseExpression(dataFilterSql);
            // 数据权限使用单独的括号 防止与其他条件冲突
            Parenthesis parenthesis = new Parenthesis(expression);
            if (ObjectUtil.isNotNull(where)) {
                return new AndExpression(where, parenthesis);
            } else {
                return parenthesis;
            }
        } catch (JSQLParserException e) {
            throw exception(DATA_SCOPE_PARSE_ERR);
        }
    }

    /**
     * 构造数据过滤sql
     */
    private String buildDataFilter(DataPermission dataPermission, LoginUser loginUser, boolean isSelect) {
        // 更新或删除需满足所有条件
        String joinStr = isSelect ? " or " : " and ";
        StandardEvaluationContext context = new StandardEvaluationContext();
        //配置bean解析器
        context.setBeanResolver(beanResolver);
        //不管是什么模板,都添加上用户id和部门id
        context.setVariable("deptId", loginUser.getDeptId());
        context.setVariable("userId", loginUser.getUserId());

        Set<String> conditions = new HashSet<>();
        for (RoleDO role : loginUser.getRoles()) {
            // 获取角色权限枚举类
            DataScopeTypeEnum type = DataScopeTypeEnum.findCode(role.getDataScope().toString());
            if (ObjectUtil.isNull(type)) {
                throw exception(DATA_SCOPE_NOT_EXISTS);
            }
            if (type.equals(DataScopeTypeEnum.ALL)) {
                return "";
            }
            //如果是自定义,多添加一个角色id
            if (type.equals(DataScopeTypeEnum.DEPT_DESIGNATE)) {
                context.setVariable("roleId", role.getId());
            }
            String[] keys = dataPermission.key();
            String[] values = dataPermission.value();
            // key和value长度不对等
            if (keys.length != values.length) {
                throw exception(DATA_SCOPE_KEY_VALUE_ERR);
            }

            // 设置注解变量 key 为表达式变量 value 为变量值
            for (int i = 0; i < keys.length; i++) {
                context.setVariable(keys[i], values[i]);
            }
            // 解析sql模板并填充
            String sql = parser.parseExpression(type.getSqlTemplate(), parserContext).getValue(context, String.class);
            conditions.add(joinStr + sql);
        }

        // 处理sql返回
        String sql = conditions.stream().filter(Objects::nonNull).collect(Collectors.joining(""));
        return sql.substring(joinStr.length());
    }

    /**
     * @param mappedStatementId 格式: 类路径.方法名
     */
    private DataPermission findAnnotation(String mappedStatementId) {
        StringBuilder sb = new StringBuilder(mappedStatementId);
        int index = sb.lastIndexOf(".");
        // 获取类路径
        String clazzName = sb.substring(0, index);
        // 获取方法名
        String methodName = sb.substring(index + 1, sb.length());
        Class<?> clazz = ClassUtil.loadClass(clazzName);
        // 找到指定方法名(可能方法是重载所以得出来集合)
        List<Method> methods = Arrays.stream(ClassUtil.getDeclaredMethods(clazz)).filter(method -> method.getName().equals(methodName)).collect(Collectors.toList());

        DataPermission dataPermission;
        // 获取方法注解
        for (Method method : methods) {
            // 优先从缓存取
            dataPermission = dataPermissionCacheMap.get(mappedStatementId);
            if (ObjectUtil.isNotNull(dataPermission)) {
                return dataPermission;
            }

            // 查找有注解的方法
            if (AnnotationUtil.hasAnnotation(method, DataPermission.class)) {
                dataPermission = AnnotationUtil.getAnnotation(method, DataPermission.class);
                dataPermissionCacheMap.put(mappedStatementId, dataPermission);
                return dataPermission;
            }
        }

        // 当方法上没有注解,查看类上是否有注解
        // 获取类注解
        if (AnnotationUtil.hasAnnotation(clazz, DataPermission.class)) {
            dataPermission = AnnotationUtil.getAnnotation(clazz, DataPermission.class);
            dataPermissionCacheMap.put(clazzName, dataPermission);
            return dataPermission;
        }

        // 如果类上方法上都没有注解,返回空对象
        return null;
    }

    /**
     * 是否为无效方法 无数据权限
     */
    public boolean isInvalid(String mappedStatementId) {
        return invalidCacheSet.contains(mappedStatementId);
    }
}
