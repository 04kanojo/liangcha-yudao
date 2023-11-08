package com.liangcha.framework.minio.service;


import org.springframework.web.multipart.MultipartFile;

/**
 * @author 凉茶
 */
public interface MinioService {

    /**
     * 保存文件，并返回文件的下载路径
     *
     * @param file     文件
     * @param filePath 文件路径
     * @param bucket   桶名称
     * @return 文件路径
     */
    String createFile(MultipartFile file, String filePath, String bucket);

//    /**
//     * 删除文件
//     *
//     * @param id 编号
//     */
//    void deleteFile(Long id) throws Exception;
//
//    /**
//     * 获得文件内容
//     *
//     * @param configId 配置编号
//     * @param path     文件路径
//     * @return 文件内容
//     */
//    byte[] getFileContent(Long configId, String path) throws Exception;

}
