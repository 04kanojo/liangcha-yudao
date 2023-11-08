package com.liangcha.system.file.service;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.liangcha.common.utils.TimeUtil;
import com.liangcha.framework.minio.service.MinioService;
import com.liangcha.system.file.dao.FileMapper;
import com.liangcha.system.file.domain.FileDO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;

import static com.liangcha.common.enums.ErrorCodeEnum.FILE_NOT_EXISTS;
import static com.liangcha.common.enums.ErrorCodeEnum.FILE_PATH_EXISTS;
import static com.liangcha.common.utils.ServiceExceptionUtil.exception;

/**
 * @author 凉茶
 */
@Service
public class FileServiceImpl implements FileService {
    @Resource
    private MinioService minioService;

    @Resource
    private FileMapper fileMapper;

    @Override
    public String createFile(MultipartFile file, String basicPath, String bucket, String fileType) {
        bucket = StrUtil.isBlank(bucket) ? "default" : bucket;
        String FileName = file.getOriginalFilename();
        String[] path = getPath(basicPath, TimeUtil.getTimeForTemplate("/yyyy/MM/dd/"), FileName);
        // 可以直接用url访问，但是无法预览的时候，比如文件是 doc 或者 docx 时，会直接下载
        String url = minioService.createFile(file, path[1], bucket);

        // 文件是否已经上传
        if (fileMapper.selectByName(FileName) != null) {
            throw exception(FILE_PATH_EXISTS);
        }

        FileDO fileDO = new FileDO();
        fileDO
                .setUuidName(path[0])
                .setBucket(bucket)
                .setType(fileType)
                .setUrl(url)
                .setName(FileName)
                .setPath(path[1])
                .setSize(file.getSize());
        fileMapper.insert(fileDO);
        return url;
    }

    @Override
    public InputStream download(String name) throws Exception {
        FileDO file = fileMapper.selectByName(name);
        if (file == null) {
            throw exception(FILE_NOT_EXISTS);
        }
        return minioService.download(file.getBucket(), file.getUuidName());
    }

    /**
     * 拼接文件路径
     * <p>例：</p>
     * <p>getPath("/test","/2023/11/1/","test.sql")</p>
     * <p>return "/test/2023/11/1/testUUID.sql"</p>
     *
     * @param basicPath 基本路径
     * @param timePath  时间路径
     * @param fileName  文件名
     * @return 字符串数组 [0]：uuid文件名 [1]：文件路径
     */
    private String[] getPath(String basicPath, String timePath, String fileName) {
        // 拼接uuid文件名
        String suffix = StrUtil.subAfter(fileName, ".", true);
        String uuid = IdUtil.fastSimpleUUID();
        String uuidFileName = uuid + "." + suffix;
        return new String[]{uuidFileName, (StrUtil.isEmpty(basicPath) ? "" : basicPath) + timePath + uuidFileName};
    }
}
