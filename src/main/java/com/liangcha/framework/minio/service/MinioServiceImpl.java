package com.liangcha.framework.minio.service;

import com.liangcha.framework.minio.MinioProperties;
import com.liangcha.framework.minio.utils.MinioUtil;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;


/**
 * 文件 Service 实现类
 *
 * @author 凉茶
 */
@Service
public class MinioServiceImpl implements MinioService {

    private static final Integer timeout = 60 * 60 * 60 * 60;
    @Resource
    private MinioProperties minioProperties;
    @Resource
    private MinioUtil minioUtil;

    @Override
    @SneakyThrows
    public String createFile(MultipartFile file, String filePath, String bucket) {
        // 1.创建桶
        minioUtil.createBucket(bucket);
        // 2.存入minio
        minioUtil.uploadFile(file.getInputStream(), file.getContentType(), file.getSize(), filePath, bucket);
        // 3.获取访问路径
        return minioUtil.getAccessUrl(bucket, filePath, Math.toIntExact(minioProperties.getExpireTimes().getSeconds()));
    }

//    @Override
//    public void deleteFile(Long id) throws Exception {
//        // 校验存在
//        FileDO file = validateFileExists(id);
//
//        // 从文件存储器中删除
//        FileClient client = fileConfigService.getFileClient(file.getConfigId());
//        Assert.notNull(client, "客户端({}) 不能为空", file.getConfigId());
//        client.delete(file.getPath());
//
//        // 删除记录
//        fileMapper.deleteById(id);
//    }

//    private FileDO validateFileExists(Long id) {
//        FileDO fileDO = fileMapper.selectById(id);
//        if (fileDO == null) {
//            throw exception(FILE_NOT_EXISTS);
//        }
//        return fileDO;
//    }

//    @Override
//    public byte[] getFileContent(Long configId, String path) throws Exception {
//        FileClient client = fileConfigService.getFileClient(configId);
//        Assert.notNull(client, "客户端({}) 不能为空", configId);
//        return client.getContent(path);
//    }

}
