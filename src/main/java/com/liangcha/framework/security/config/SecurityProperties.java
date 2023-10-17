package com.liangcha.framework.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

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
}
