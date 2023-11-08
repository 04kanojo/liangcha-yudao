package com.liangcha.server.system.controller.role.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @author 凉茶
 */
@ApiModel(description = "管理后台 - 角色更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleUpdateReqVO extends RoleBaseVO {

    @ApiModelProperty("角色编号")
    @NotNull(message = "角色编号不能为空")
    private Long id;

}
