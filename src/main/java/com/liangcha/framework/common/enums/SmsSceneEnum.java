package com.liangcha.framework.common.enums;

import cn.hutool.core.util.ArrayUtil;
import com.liangcha.framework.validation.interfaces.IntArr;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 用户短信验证码发送场景的枚举
 *
 * @author 凉茶
 */
@Getter
@AllArgsConstructor
public enum SmsSceneEnum implements IntArr {

    MEMBER_LOGIN(1, "user-sms-login", "会员用户 - 手机号登陆"),
    MEMBER_UPDATE_MOBILE(2, "user-update-mobile", "会员用户 - 修改手机"),
    MEMBER_UPDATE_PASSWORD(3, "user-update-mobile", "会员用户 - 修改密码"),
    MEMBER_RESET_PASSWORD(4, "user-reset-password", "会员用户 - 忘记密码"),

    ADMIN_MEMBER_LOGIN(21, "admin-sms-login", "后台用户 - 手机号登录");

    /**
     * 验证场景的编号
     */
    private final Integer scene;

    /**
     * 模版编码
     */
    private final String templateCode;

    /**
     * 描述
     */
    private final String description;

    public static SmsSceneEnum getByScene(Integer scene) {
        return ArrayUtil.firstMatch(sceneEnum -> sceneEnum.getScene().equals(scene),
                values());
    }

    @Override
    public int[] getIntArr() {
        return Arrays.stream(values()).mapToInt(SmsSceneEnum::getScene).toArray();
    }

}
