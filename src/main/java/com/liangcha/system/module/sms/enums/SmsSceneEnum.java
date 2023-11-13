package com.liangcha.system.module.sms.enums;

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

    ADMIN_LOGIN(1, "1978630", "后台用户 - 手机号登录");

    /**
     * 验证场景的编号
     */
    private final Integer scene;

    /**
     * 模版id
     * 对应短信平台的模板id
     */
    private final String templateId;

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
