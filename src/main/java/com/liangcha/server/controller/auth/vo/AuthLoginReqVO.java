package com.liangcha.server.controller.auth.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * @author 凉茶
 */
@ApiModel("管理后台-账号密码登录请求VO，如果登录并绑定社交用户，需要传递 social 开头的参数")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthLoginReqVO {

    @NotEmpty(message = "登录账号不能为空")
    @Length(min = 4, max = 16, message = "账号长度为 4-16 位")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "账号格式为数字以及字母")
    private String username;

    @NotEmpty(message = "密码不能为空")
    @Length(min = 4, max = 16, message = "密码长度为 4-16 位")
    private String password;


    @NotEmpty(message = "验证码不能为空")
    private String captchaVerification;


    // ========== 绑定社交登录时，需要传递如下参数 ==========

//    @InEnum(value = SocialTypeEnum.class)
//    private Integer socialType;
//
//    @NotEmpty(message = "授权码不能为空")
//    private String socialCode;
//
//    @NotEmpty(message = "授权 state 不能为空")
//    private String socialState;

}
