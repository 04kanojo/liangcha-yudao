package com.liangcha.server.system.controller.file;

import com.liangcha.common.pojo.CommonResult;
import com.liangcha.server.system.controller.file.vo.FileUploadReqVO;
import com.liangcha.system.file.enums.FileTypeEnum;
import com.liangcha.system.file.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

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
//    @GetMapping("/{configId}/get/**")
//    @PermitAll
//    @ApiOperation("下载文件")
//    public void getFileContent(HttpServletRequest request, HttpServletResponse response, @PathVariable("configId") Long configId) throws Exception {
//        // 获取请求的路径
//        String path = StrUtil.subAfter(request.getRequestURI(), "/get/", false);
//        if (StrUtil.isEmpty(path)) {
//            throw new IllegalArgumentException("结尾的 path 路径必须传递");
//        }
//
//        // 读取内容
//        byte[] content = minioService.getFileContent(configId, path);
//        if (content == null) {
//            log.warn("[getFileContent][configId({}) path({}) 文件不存在]", configId, path);
//            response.setStatus(HttpStatus.NOT_FOUND.value());
//            return;
//        }
//        ServletUtils.writeAttachment(response, path, content);
//    }

}
