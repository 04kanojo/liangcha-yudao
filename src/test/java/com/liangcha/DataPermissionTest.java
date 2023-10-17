package com.liangcha;

import com.liangcha.framework.captcha.CaptchaProperties;
import com.liangcha.framework.security.config.SecurityProperties;
import com.liangcha.system.user.dao.AdminUserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.Duration;

@SpringBootTest
public class DataPermissionTest {

    @Resource
    private AdminUserMapper mapper;

    @Resource
    private CaptchaProperties properties;

    @Resource
    private SecurityProperties securityProperties;

    @Test
    void test() {
        Duration expireTimes = properties.getExpireTimes();
        long seconds = expireTimes.getSeconds();
        System.out.println(seconds);

        Duration tokenExpireTimes = securityProperties.getTokenExpireTimes();
        System.out.println(tokenExpireTimes.toMinutes());

        Duration refreshTokenExpireTimes = securityProperties.getRefreshTokenExpireTimes();
        System.out.println(refreshTokenExpireTimes.toDays());

//        List<AdminUserDO> adminUserDOS = mapper.selectList(null);
//        System.out.println(adminUserDOS);
    }
}
