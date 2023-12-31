package com.liangcha.framework.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;
import java.util.List;

/**
 * @author 凉茶
 */
@Data
@ConfigurationProperties(prefix = "liangcha.security")
public class SecurityProperties {

    /**
     * HTTP 请求时，访问令牌的请求 Header
     */
    private String tokenHeader = "Authorization";

    /**
     * 免登录的 URL 列表
     */
    private List<String> permitAllUrls;

    /**
     * PasswordEncoder 加密复杂度，越高开销越大
     */
    private Integer passwordEncoderLength = 4;

    /**
     * Bearer代表 OAuth2
     */
    private String authorizationBearer = "Bearer";

    /**
     * token过期时间
     */
    private Duration tokenExpireTimes = Duration.ofMinutes(30);

    /**
     * refreshToken过期时间
     */
    private Duration refreshTokenExpireTimes = Duration.ofDays(3);

    /**
     * client过期时间
     */
    private Duration clientExpireTimes = Duration.ofDays(10);

    /**
     * auth2Code过期时间
     */
    private Duration auth2CodeExpireTimes = Duration.ofMinutes(5);


    /**
     * 授权信息过期时间
     */
    private Duration approveExpireTimes = Duration.ofDays(30);
}
