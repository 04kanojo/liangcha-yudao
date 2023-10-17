package com.liangcha.framework.captcha;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

/**
 * @author 凉茶
 */
@Data
@ConfigurationProperties(prefix = "liangcha.captcha")
public class CaptchaProperties {

    /**
     * 验证码开关
     */
    private Boolean enable = true;

    /**
     * 验证码过期时间
     * 默认三分钟
     */
    private Duration expireTimes = Duration.ofSeconds(180);
}
