package com.liangcha.server.controller.sms.dto;

import com.liangcha.framework.validation.annotation.InEnum;
import com.liangcha.framework.validation.annotation.Mobile;
import com.liangcha.system.sms.enums.SmsSceneEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 短信验证码的发送 Request DTO
 *
 * @author 凉茶
 */
@Data
@Accessors(chain = true)
public class SmsCodeSendReqDTO {

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
     * 发送 IP
     */
    @NotEmpty(message = "发送 IP 不能为空")
    private String createIp;

}
