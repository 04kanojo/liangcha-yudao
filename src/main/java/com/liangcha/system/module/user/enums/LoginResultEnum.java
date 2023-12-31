package com.liangcha.system.module.user.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 登录结果的枚举类
 *
 * @author 凉茶
 */
@Getter
@AllArgsConstructor
public enum LoginResultEnum {

    SUCCESS(0), // 成功

    BAD_CREDENTIALS(10), // 账号或密码不正确

    USER_DISABLED(20), // 用户被禁用

    CAPTCHA_NOT_FOUND(30), // 验证码不存在

    CAPTCHA_CODE_ERROR(31), // 验证码不正确

    CAPTCHA_CODE_EXPIRED(31), // 验证码过期

    ;

    /**
     * 结果
     */
    private final Integer result;

}
