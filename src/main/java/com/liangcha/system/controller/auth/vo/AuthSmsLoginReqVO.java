package com.liangcha.system.controller.auth.vo;

import com.liangcha.framework.validation.annotation.Mobile;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@ApiModel(description = "管理后台 - 短信验证码的登录 Request VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthSmsLoginReqVO {

    @ApiModelProperty("手机号")
    @NotEmpty(message = "手机号不能为空")
    @Mobile
    private String mobile;

    @ApiModelProperty("短信验证码")
    @NotEmpty(message = "验证码不能为空")
    private String code;

}
