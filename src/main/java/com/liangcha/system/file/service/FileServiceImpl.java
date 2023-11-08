package com.liangcha.system.file.service;

import com.liangcha.framework.minio.service.MinioService;
import com.liangcha.system.file.dao.FileMapper;
import com.liangcha.system.file.domain.FileDO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@Service
public class FileServiceImpl implements FileService {
    @Resource
    private MinioService minioService;

    @Resource
    private FileMapper fileMapper;

    @Override
    public String createFile(MultipartFile file, String filePath, String bucket, String fileType) {
        // 无法预览时也会直接下载
        String url = minioService.createFile(file, filePath, bucket);
        FileDO fileDO = new FileDO();
        fileDO
                .setType(fileType)
                .setUrl(url)
                .setName(file.getOriginalFilename())
                .setPath(filePath)
                .setSize(file.getSize());
        fileMapper.insert(fileDO);
        return url;
    }

}
