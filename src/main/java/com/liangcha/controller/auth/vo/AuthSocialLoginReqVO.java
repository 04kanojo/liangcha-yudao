package com.liangcha.controller.auth.vo;

import com.liangcha.framework.common.enums.SocialTypeEnum;
import com.liangcha.framework.common.validation.InEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author 凉茶
 */
@ApiModel(description = "管理后台 - 社交绑定登录 Request VO，使用 code 授权码 + 账号密码")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthSocialLoginReqVO {

    @ApiModelProperty("社交平台的类型")
    @InEnum(SocialTypeEnum.class)
    @NotNull(message = "社交平台的类型不能为空")
    private Integer type;

    @ApiModelProperty("授权码")
    @NotEmpty(message = "授权码不能为空")
    private String code;

    @ApiModelProperty("state")
    @NotEmpty(message = "state 不能为空")
    private String state;

}
