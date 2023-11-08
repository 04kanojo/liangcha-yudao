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
        String filename = file.getOriginalFilename();
        String path = getPath(basicPath, TimeUtil.getTimeForTemplate("/yyyy/MM/dd/"), filename);
        // 可以直接用url访问，但是无法预览的时候，比如文件是 doc 或者 docx 时，会直接下载
        String url = minioService.createFile(file, path, StrUtil.isBlank(bucket) ? "default" : bucket);
        FileDO fileDO = new FileDO();
        fileDO
                .setType(fileType)
                .setUrl(url)
                .setName(filename)
                .setPath(path)
                .setSize(file.getSize());
        fileMapper.insert(fileDO);
        return url;
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
     */
    private String getPath(String basicPath, String timePath, String fileName) {
        String suffix = StrUtil.subAfter(fileName, ".", true);
        // 获取基本信息
        return (StrUtil.isEmpty(basicPath) ? "" : basicPath) + timePath + IdUtil.fastSimpleUUID() + "." + suffix;
    }
}
