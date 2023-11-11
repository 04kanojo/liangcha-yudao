package com.liangcha.framework.minio.utils;

import cn.hutool.core.util.StrUtil;
import io.minio.*;
import io.minio.http.Method;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.InputStream;

import static com.liangcha.common.enums.ErrorCodeEnum.FILE_BUCKET_LENGTH_ERR;
import static com.liangcha.common.utils.ServiceExceptionUtil.exception;

/**
 * @author 凉茶
 */
@Component
public class MinioUtil {
    @Resource
    private MinioClient minioClient;

    /**
     * 创建一个桶
     *
     * @param bucket 桶名称
     */
    @SneakyThrows
    public void createBucket(String bucket) {
        boolean found;
        try {
            found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build());
        } catch (IllegalArgumentException exception) {
            throw exception(FILE_BUCKET_LENGTH_ERR);
        }
        if (!found) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
            setBucketPolicy(bucket);
        }
    }

    /**
     * 上传一个文件
     *
     * @param stream      文件流
     * @param contentType 文件类型
     * @param size        文件大小
     * @param bucket      桶名称
     */
    @SneakyThrows
    public void uploadFile(InputStream stream, String contentType, Long size, String name, String bucket) {
        PutObjectArgs build = PutObjectArgs.builder()
                .bucket(bucket)
                .object(name)
                .contentType(contentType)
                .stream(stream, size, -1).build();
        minioClient.putObject(build);
        // 不关闭流会报错
        stream.close();
    }


    /**
     * 生成访问链接，即使是私有
     * 图片访问是预览，文档访问（如doc）是下载
     *
     * @param bucket  桶名称
     * @param name    名字
     * @param expires 过期时间
     */
    @SneakyThrows
    public String getAccessUrl(String bucket, String name, Integer expires) {
        GetPresignedObjectUrlArgs build = GetPresignedObjectUrlArgs
                .builder()
                .bucket(bucket)
                .object(name)
                .expiry(expires)
                .method(Method.GET)
                .build();

        return minioClient.getPresignedObjectUrl(build);
    }

    /**
     * 下载文件
     *
     * @param bucket 桶名称
     * @param name   文件路径
     */
    @SneakyThrows
    public InputStream download(String bucket, String name) {
        return minioClient.getObject(GetObjectArgs
                .builder()
                .bucket(bucket)
                .object(name)
                .build());
    }

    /**
     * 删除一个对象
     *
     * @param bucket 桶名称
     * @param path   文件路径
     */
    @SneakyThrows
    public void delete(String bucket, String path) {
        minioClient.removeObject(RemoveObjectArgs
                .builder()
                .bucket(bucket)
                .object(path)
                .build());
    }

    /**
     * 设置桶策略(固定设置为公有，不设置默认私有)
     *
     * @param bucket 桶名称
     */
    @SneakyThrows
    public void setBucketPolicy(String bucket) {
        String config = "{\n" +
                "    \"Version\": \"2012-10-17\",\n" +
                "    \"Statement\": [\n" +
                "        {\n" +
                "            \"Effect\": \"Allow\",\n" +
                "            \"Principal\": {\n" +
                "                \"AWS\": [\n" +
                "                    \"*\"\n" +
                "                ]\n" +
                "            },\n" +
                "            \"Action\": [\n" +
                "                \"s3:GetBucketLocation\",\n" +
                "                \"s3:ListBucket\",\n" +
                "                \"s3:ListBucketMultipartUploads\"\n" +
                "            ],\n" +
                "            \"Resource\": [\n" +
                "                \"arn:aws:s3:::bucketName\"\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"Effect\": \"Allow\",\n" +
                "            \"Principal\": {\n" +
                "                \"AWS\": [\n" +
                "                    \"*\"\n" +
                "                ]\n" +
                "            },\n" +
                "            \"Action\": [\n" +
                "                \"s3:AbortMultipartUpload\",\n" +
                "                \"s3:DeleteObject\",\n" +
                "                \"s3:GetObject\",\n" +
                "                \"s3:ListMultipartUploadParts\",\n" +
                "                \"s3:PutObject\"\n" +
                "            ],\n" +
                "            \"Resource\": [\n" +
                "                \"arn:aws:s3:::bucketName/*\"\n" +
                "            ]\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        config = StrUtil.replace(config, "bucketName", bucket);
        System.out.println(bucket);
        minioClient.setBucketPolicy(SetBucketPolicyArgs
                .builder()
                .bucket(bucket)
                .config(config)
                .build());
    }
}
