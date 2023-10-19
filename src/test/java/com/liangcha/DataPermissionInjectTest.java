package com.liangcha;

import cn.hutool.extra.spring.SpringUtil;
import com.liangcha.framework.captcha.CaptchaProperties;
import com.liangcha.framework.security.config.SecurityProperties;
import com.liangcha.system.auth.domain.AdminUserDO;
import com.liangcha.system.user.dao.AdminUserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.List;

@SpringBootTest
public class DataPermissionInjectTest {

    @Resource
    private AdminUserMapper mapper;

    @Resource
    private CaptchaProperties properties;

    @Resource
    private SecurityProperties securityProperties;

    @Test
    void testDuration() {
        Duration expireTimes = properties.getExpireTimes();
        long seconds = expireTimes.getSeconds();
        System.out.println(seconds);

        Duration tokenExpireTimes = securityProperties.getTokenExpireTimes();
        System.out.println(tokenExpireTimes.toMinutes());

        Duration refreshTokenExpireTimes = securityProperties.getRefreshTokenExpireTimes();
        System.out.println(refreshTokenExpireTimes.toDays());
    }

    @Test
    void testDataPermission() {
        List<AdminUserDO> adminUserDOS = mapper.selectList(null);
        System.out.println(adminUserDOS);
    }

    @Test
    void testSqel() {
        String tmp = " #{#deptName} IN ( #{@roleServiceImpl.getDesignateDeptById( #roleId )} ";
        // 解析器
        ExpressionParser parser = new SpelExpressionParser();

        // 上下文对象
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setBeanResolver(new BeanFactoryResolver(SpringUtil.getBeanFactory()));
        context.setVariable("deptName", "dept_id");
        context.setVariable("roleId", "101");

        //解析模板
        Expression expression = parser.parseExpression(tmp, new TemplateParserContext());

        //解析
        String value = expression.getValue(context, String.class);
        System.out.println(value);
    }

}
