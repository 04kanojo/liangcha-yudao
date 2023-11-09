package com.liangcha.framework.minio.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 凉茶
 */
@Data
@ConfigurationProperties(prefix = "minio")
public class MinioProperties {
    private String url;

    private String accessKey;

    private String secretKey;
}
