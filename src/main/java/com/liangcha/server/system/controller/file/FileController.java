package com.liangcha.server.system.controller.file;

import com.liangcha.common.pojo.CommonResult;
import com.liangcha.server.system.controller.file.vo.FileUploadReqVO;
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
import java.net.URLEncoder;

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
        String type = getFileType(uploadReqVO.getType());
        return success(fileService.createFile(
                uploadReqVO.getFile(),
                uploadReqVO.getBasicPath(),
                type));
    }

    /**
     * 校验文件类型是否存在
     */
    private String getFileType(String type) {
        switch (type) {
            case "avatar":
                return AVATAR.getType();

            case "test":
                return TEST.getType();
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

    @ApiOperation(value = "下载文件", produces = "application/octet-stream")
    @GetMapping(value = "/download/{type}/{name}")
    public void downloadFile(HttpServletResponse response, @PathVariable String name, @PathVariable String type) throws Exception {
        InputStream inputStream = fileService.download(name, getFileType(type));
        ServletOutputStream outputStream = response.getOutputStream();
        // 中文设置为filename报错，先使用utf-8进行编码
        String encodeName = URLEncoder.encode(name, "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + encodeName);
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("UTF-8");
        IOUtils.copy(inputStream, outputStream);
        inputStream.close();
        outputStream.close();
    }

}
