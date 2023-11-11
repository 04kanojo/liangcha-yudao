package com.liangcha.system.file.service;

import com.liangcha.system.file.domain.FileDO;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @author 凉茶
 */
public interface FileService {
    String createFile(MultipartFile file, String bucket);

    InputStream download(FileDO file);

    void insert(FileDO fileDO);

    FileDO getById(Long id);

    void delete(FileDO file);
}
