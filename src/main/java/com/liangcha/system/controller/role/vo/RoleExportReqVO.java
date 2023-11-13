package com.liangcha.system.controller.role.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static com.liangcha.common.utils.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
 * @author 凉茶
 */
@ApiModel("管理后台 - 角色分页 Request VO")
@Data
public class RoleExportReqVO {

    @ApiModelProperty("角色名称")
    private String name;

    @ApiModelProperty("角色标识")
    private String code;

    @ApiModelProperty("展示状态")
    private Integer status;

    @ApiModelProperty("开始时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
