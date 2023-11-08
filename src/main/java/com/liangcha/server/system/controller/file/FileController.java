package com.liangcha.server.system.controller.file;

import com.liangcha.common.pojo.CommonResult;
import com.liangcha.server.system.controller.file.vo.FileUploadReqVO;
import com.liangcha.system.file.enums.FileTypeEnum;
import com.liangcha.system.file.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.InputStream;

import static com.liangcha.common.enums.ErrorCodeEnum.FILE_TYPE_ERR;
import static com.liangcha.common.pojo.CommonResult.success;
import static com.liangcha.common.utils.ServiceExceptionUtil.exception;
import static com.liangcha.system.file.enums.FileTypeEnum.AVATAR;
import static com.liangcha.system.file.enums.FileTypeEnum.TEST;


/**
 * @author 凉茶
 */
@Api(tags = "管理后台 - 文件存储")
@RestController
@RequestMapping("/system/file")
@Slf4j
public class FileController {

    @Resource
    private FileService fileService;

    @PostMapping("/upload")
    @ApiOperation("上传文件")
    public CommonResult<String> uploadFile(@Valid FileUploadReqVO uploadReqVO) {
        return success(fileService.createFile(
                uploadReqVO.getFile(),
                uploadReqVO.getBasicPath(),
                uploadReqVO.getBucket(),
                getFileType(uploadReqVO.getType()).getType()));
    }

    private FileTypeEnum getFileType(String type) {
        switch (type) {
            case "avatar":
                return AVATAR;

            case "test":
                return TEST;
        }
        throw exception(FILE_TYPE_ERR);
    }

    //    @DeleteMapping("/delete")
//    @ApiOperation("删除文件")
//    @PreAuthorize("@ss.hasPermission('infra:file:delete')")
//    public CommonResult<Boolean> deleteFile(@RequestParam("id") Long id) throws Exception {
//        minioService.deleteFile(id);
//        return success(true);
//    }
//
    @ApiOperation("下载文件")
    @GetMapping(value = "/download/{name}")
    public void downloadFile(HttpServletResponse response, @PathVariable String name) throws Exception {
        InputStream stream = fileService.download(name);
        ServletOutputStream output = response.getOutputStream();
        response.setHeader("Content-Disposition", "attachment;filename=" + name);
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("UTF-8");
        IOUtils.copy(stream, output);
        stream.close();
        output.close();
    }

}
