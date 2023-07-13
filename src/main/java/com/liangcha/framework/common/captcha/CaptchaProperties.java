package com.liangcha.framework.common.captcha;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

/**
 * @author 凉茶
 */
@Validated
@Data
@ConfigurationProperties(prefix = "liangcha")
public class CaptchaProperties {

    /**
     * 验证码开关
     */
    private Boolean captcha = true;
}
