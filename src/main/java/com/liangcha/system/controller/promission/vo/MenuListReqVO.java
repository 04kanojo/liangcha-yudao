package com.liangcha.system.controller.promission.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 凉茶
 */
@ApiModel(description = "管理后台 - 菜单列表 Request VO")
@Data
public class MenuListReqVO {

    @ApiModelProperty("菜单名称，模糊匹配")
    private String name;

    @ApiModelProperty("展示状态，参见 CommonStatusEnum 枚举类")
    private Integer status;

}
