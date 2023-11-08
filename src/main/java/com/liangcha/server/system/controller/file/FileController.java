package com.liangcha.server.system.controller.file;

import com.liangcha.common.pojo.CommonResult;
import com.liangcha.common.utils.TimeUtil;
import com.liangcha.framework.minio.service.MinioService;
import com.liangcha.server.system.controller.file.vo.FileUploadReqVO;
import com.liangcha.system.file.enums.FileTypeEnum;
import com.liangcha.system.file.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;

import static com.liangcha.common.enums.ErrorCodeEnum.FILE_TYPE_ERR;
import static com.liangcha.common.pojo.CommonResult.success;
import static com.liangcha.common.utils.ServiceExceptionUtil.exception;


@Api(tags = "管理后台 - 文件存储")
@RestController
@RequestMapping("/system/file")
public class FileController {

    @Resource
    private MinioService minioService;

    @Resource
    private FileService fileService;

    @PostMapping("/upload")
    @ApiOperation("上传文件")
    public CommonResult<String> uploadFile(@Valid FileUploadReqVO uploadReqVO) {
        // 获取基本信息
        FileTypeEnum fileType = getFileType(uploadReqVO.getType());
        MultipartFile file = uploadReqVO.getFile();
        String bucket = uploadReqVO.getBucket();
        String path = uploadReqVO.getPath();
        // 拼接文件路径
        path = getPath(path == null ? "" : path, TimeUtil.getTimeForTemplate("/yyyy/MM/dd/"), file.getOriginalFilename());


        return success(fileService.createFile(file, path, bucket == null ? "default" : bucket, fileType.getType()));
    }

    /**
     * 拼接文件路径
     *
     * @param basicPath 基本路径
     * @param timePath  时间路径
     * @param fileName  文件名
     */
    private String getPath(String basicPath, String timePath, String fileName) {
        StringBuilder sb = new StringBuilder(basicPath);
        sb.append(timePath).append(fileName);
        return sb.toString();
    }

    private FileTypeEnum getFileType(String type) {
        switch (type) {
            case "avatar":
                return FileTypeEnum.AVATAR;
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
