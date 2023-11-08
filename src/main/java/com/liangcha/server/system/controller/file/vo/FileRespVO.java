package com.liangcha.server.system.controller.file.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@ApiModel("管理后台 - 文件 Response VO,不返回 content 字段，太大")
@Data
public class FileRespVO {

    @ApiModelProperty("文件编号")
    private Long id;

    @ApiModelProperty("配置编号")
    private Long configId;

    @ApiModelProperty("文件路径")
    private String path;

    @ApiModelProperty("原文件名")
    private String name;

    @ApiModelProperty("文件 URL")
    private String url;

    @ApiModelProperty("文件MIME类型")
    private String type;

    @ApiModelProperty("文件大小")
    private Integer size;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

}
