package com.liangcha.framework.minio.utils;

import io.minio.*;
import io.minio.http.Method;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.InputStream;

@Component
public class MinioUtil {
    @Resource
    private MinioClient minioClient;

    /**
     * 创建一个桶
     */
    public void createBucket(String bucket) throws Exception {
        boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build());
        if (!found) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
        }
    }

    /**
     * 上传一个文件
     */
    public void uploadFile(InputStream stream, String contentType, String name, String bucket) throws Exception {
        // 不指定contentType获取访问链接，浏览器会直接下载不会生成预览图
        PutObjectArgs build = PutObjectArgs.builder()
                .bucket(bucket)
                .object(name)
                .contentType(contentType)
                .stream(stream, -1, 10485760)
                .build();
        minioClient.putObject(build);
        // 不关闭流会报错
        stream.close();
    }

    @SuppressWarnings("all")
    /**
     * 生成一个给HTTP GET请求用的presigned URL用户可以用这个URL进行下载，即使其所在的存储桶是私有的
     */
    public String getAccessUrl(String bucket, String name, Integer expires) throws Exception {
        GetPresignedObjectUrlArgs build = GetPresignedObjectUrlArgs.builder()
                .bucket(bucket)
                .object(name)
                .expiry(expires)
                .method(Method.GET)
                .build();

        return minioClient.getPresignedObjectUrl(build);
    }

}
