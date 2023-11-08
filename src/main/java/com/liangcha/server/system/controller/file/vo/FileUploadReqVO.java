package com.liangcha.server.system.controller.file.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@ApiModel(description = "管理后台 - 上传文件 Request VO")
@Data
public class FileUploadReqVO {

    @ApiModelProperty("文件附件")
    @NotNull(message = "文件附件不能为空")
    private MultipartFile file;

    @ApiModelProperty("基本文件路径")
    @Pattern(regexp = "^\\/[^/]+(\\/[^/]+)*\\/?$", message = "路径必须用/开头")
    private String basicPath;

    @ApiModelProperty("桶名称")
    private String bucket;

    @ApiModelProperty("上传类型")
    @NotNull(message = "上传类型不能为空")
    private String type;
}
