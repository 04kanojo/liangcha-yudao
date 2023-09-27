package com.liangcha.system.controller.role.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@ApiModel("管理后台 - 角色信息 Response VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RoleRespVO extends RoleBaseVO {

    @ApiModelProperty("角色编号")
    private Long id;

    @ApiModelProperty("数据范围")
    private Integer dataScope;

    @ApiModelProperty("数据范围(指定部门数组)")
    private Set<Long> dataScopeDeptIds;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("角色类型")
    private Integer type;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

}
