package com.liangcha.system.controller.file.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * @author 凉茶
 */
@ApiModel(description = "管理后台 - 上传文件 Request VO")
@Data
public class FileUploadReqVO {

    @ApiModelProperty("文件附件")
    @NotNull(message = "文件附件不能为空")
    private MultipartFile file;

    @ApiModelProperty("上传类型")
    @NotNull(message = "上传类型不能为空")
    private String type;
}
