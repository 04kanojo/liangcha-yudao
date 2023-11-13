package com.liangcha.system.module.sms.dto;

import com.liangcha.framework.validation.annotation.InEnum;
import com.liangcha.framework.validation.annotation.Mobile;
import com.liangcha.system.module.sms.enums.SmsSceneEnum;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 短信验证码的使用 Request DTO
 *
 * @author 凉茶
 */
@Data
public class SmsCodeUseReqDTO {

    /**
     * 手机号
     */
    @Mobile
    @NotEmpty(message = "手机号不能为空")
    private String mobile;

    /**
     * 发送场景
     */
    @NotNull(message = "发送场景不能为空")
    @InEnum(SmsSceneEnum.class)
    private Integer scene;

    /**
     * 验证码
     */
    @NotEmpty(message = "验证码")
    private String code;

    /**
     * 使用 IP
     */
    @NotEmpty(message = "使用 IP 不能为空")
    private String usedIp;

}
