package com.liangcha.server.system.controller.role.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "管理后台 - 角色精简信息 Response VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleSimpleRespVO {

    @ApiModelProperty("角色编号")
    private Long id;

    @ApiModelProperty("角色名称")
    private String name;

}
