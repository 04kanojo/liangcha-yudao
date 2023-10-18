package com.liangcha.framework.security.config;

import com.liangcha.framework.security.service.SecurityFrameworkService;
import com.liangcha.framework.security.service.impl.SecurityFrameworkServiceImpl;
import com.liangcha.system.permission.service.PermissionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

/**
 * 用于声明SpringSecurity需要的java bean
 */
@Configuration
public class SecurityBeanConfig {
    @Resource
    private SecurityProperties securityProperties;

    /**
     * Spring Security 加密器
     * 考虑到安全性，这里采用 BCryptPasswordEncoder 加密器
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(securityProperties.getPasswordEncoderLength());
    }

    @Bean("ss") // 使用 Spring Security 的缩写，方便使用
    public SecurityFrameworkService securityFrameworkService(PermissionService permissionService) {
        return new SecurityFrameworkServiceImpl(permissionService);
    }

}
