package com.liangcha.server.controller.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @author 凉茶
 */
@ApiModel(description = "管理后台 - 用户更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserUpdateReqVO extends UserBaseVO {

    @ApiModelProperty("用户编号")
    @NotNull(message = "用户编号不能为空")
    private Long id;

}
