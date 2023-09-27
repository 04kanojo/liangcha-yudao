package com.liangcha.system.controller.role.vo;

import com.liangcha.framework.common.enums.CommonStatusEnum;
import com.liangcha.framework.validation.InEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@ApiModel(description = "管理后台 - 角色更新状态 Request VO")
@Data
public class RoleUpdateStatusReqVO {

    @ApiModelProperty("角色编号")
    @NotNull(message = "角色编号不能为空")
    private Long id;

    @ApiModelProperty("状态")
    @NotNull(message = "状态不能为空")
    @InEnum(value = CommonStatusEnum.class, message = "修改状态必须是 {value}")
    private Integer status;

}
