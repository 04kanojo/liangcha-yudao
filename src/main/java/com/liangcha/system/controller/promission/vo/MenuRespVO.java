package com.liangcha.system.controller.promission.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author 凉茶
 */
@ApiModel(description = "管理后台 - 菜单信息 Response VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MenuRespVO extends MenuBaseVO {

    @ApiModelProperty("菜单编号")
    private Long id;

    @ApiModelProperty("状态，参见 CommonStatusEnum 枚举类")
    private Integer status;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

}
