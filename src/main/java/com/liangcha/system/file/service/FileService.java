package com.liangcha.system.file.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    String createFile(MultipartFile file, String filePath, String bucket, String fileType);
}
