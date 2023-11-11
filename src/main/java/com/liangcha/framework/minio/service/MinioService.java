package com.liangcha.framework.minio.service;


import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @author 凉茶
 */
public interface MinioService {

    /**
     * 保存文件，并返回文件的访问路径
     *
     * @param file     文件
     * @param filePath 文件路径
     * @param bucket   桶名称
     * @return 文件路径
     */
    String createFile(MultipartFile file, String filePath, String bucket);

    /**
     * 下载文件
     *
     * @param bucket 桶名称
     * @param path   文件路径
     * @return 文件流
     */
    InputStream download(String bucket, String path);

    /**
     * 删除文件
     *
     * @param bucket 桶名称
     * @param path   文件路径
     */
    void delete(String bucket, String path);

}
