package com.liangcha.system.controller.auth.vo;

import com.liangcha.framework.validation.annotation.InEnum;
import com.liangcha.framework.validation.annotation.Mobile;
import com.liangcha.system.module.sms.enums.SmsSceneEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author 凉茶
 */
@ApiModel(description = "管理后台 - 发送手机验证码 Request VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthSmsSendReqVO {

    @ApiModelProperty("手机号")
    @NotEmpty(message = "手机号不能为空")
    @Mobile
    private String mobile;

    @ApiModelProperty("短信场景")
    @NotNull(message = "发送场景不能为空")
    @InEnum(SmsSceneEnum.class)
    private Integer scene;

}
