package com.liangcha.controller.auth.vo;

import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("管理后台-账号密码登录请求VO，如果登录并绑定社交用户，需要传递 social 开头的参数")
public class AuthLoginReqVO {

    @NotEmpty(message = "登录账号不能为空")
//    @Min(value = 4, message = "账号长度最少为4位")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "账号格式为数字以及字母")
    private String username;

    @NotEmpty(message = "密码不能为空")
//    @Min(value = 4, message = "密码长度最少为4位")
    private String password;

    // ========== 图片验证码相关 ==========

    @NotEmpty(message = "验证码不能为空", groups = CodeEnableGroup.class)
    private String captchaVerification;

    // ========== 绑定社交登录时，需要传递如下参数 ==========

    private Integer socialType;

    private String socialCode;

    private String socialState;

    @AssertTrue(message = "授权码不能为空")
    public boolean isSocialCodeValid() {
        return socialType == null || StrUtil.isNotEmpty(socialCode);
    }

    @AssertTrue(message = "授权 state 不能为空")
    public boolean isSocialState() {
        return socialType == null || StrUtil.isNotEmpty(socialState);
    }

    /**
     * 开启验证码的 Group
     */
    public interface CodeEnableGroup {
    }

}
