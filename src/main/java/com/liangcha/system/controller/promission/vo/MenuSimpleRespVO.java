package com.liangcha.system.controller.promission.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("管理后台 - 菜单精简信息 Response VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuSimpleRespVO {

    @ApiModelProperty("菜单编号")
    private Long id;

    @ApiModelProperty("菜单名称")
    private String name;

    @ApiModelProperty("父菜单 ID")
    private Long parentId;

    @ApiModelProperty("类型，参见 MenuTypeEnum 枚举类")
    private Integer type;

}
