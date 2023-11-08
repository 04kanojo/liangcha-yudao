package com.liangcha.system.file.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @author 凉茶
 */
public interface FileService {
    String createFile(MultipartFile file, String basicPath, String bucket, String fileType);

    InputStream download(String name) throws Exception;
}
