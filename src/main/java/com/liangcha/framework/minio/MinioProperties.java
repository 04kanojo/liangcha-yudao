package com.liangcha.framework.minio;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@Data
@ConfigurationProperties(prefix = "minio")
public class MinioProperties {
    private String url;

    private String accessKey;

    private String secretKey;

    /**
     * 访问链接过期时间，默认7天
     * minio文件访问路径，最短1s，最长7d
     */
    private Duration expireTimes = Duration.ofDays(7);
}
