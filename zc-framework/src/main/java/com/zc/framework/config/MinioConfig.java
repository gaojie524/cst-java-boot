package com.zc.framework.config;

import io.minio.MinioClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "minio")
public class MinioConfig {
    /**
     * 扫描配置文件，获取minio的相关配置信息
     */
    private String endpoint;
    private String accessKey;
    private String secretKey;
    private String bucketName;

    /**
     * 构建 minioClient 客户端
     */
    @Bean
    public MinioClient minioClient() {

        return MinioClient.builder()
                .endpoint(endpoint)
                .credentials(accessKey, secretKey)
                .build();
    }

}