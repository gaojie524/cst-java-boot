package com.zc.web.util;

import com.alibaba.fastjson2.util.DateUtils;
import com.zc.common.utils.StringUtils;
import com.zc.framework.config.MinioConfig;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.Date;

@Component
@Slf4j
public class MinioUtils {

    /**
     * 构造器注入 minioClient、minioConfig
     */
    private final MinioClient minioClient;

    private final MinioConfig minioConfig;

    public MinioUtils(MinioClient minioClient,MinioConfig minioConfig) {
        this.minioClient = minioClient;
        this.minioConfig = minioConfig;
    }

    /**
     * 上传文件到minio
     *
     * @param file 文件
     * @return 文件存储的路径
     */
    public String uploadFile(MultipartFile file) {

        String originalFilename = file.getOriginalFilename();

        if (StringUtils.isBlank(originalFilename)) {
            throw new RuntimeException();
        }
        // 重新命名文件，文件名为 uuid 生成
        String fileName = UuidUtils.getUuid() + originalFilename.substring(originalFilename.lastIndexOf("."));
        // 设置存储目录，设置 年-月和日两级目录
        String objectName = DateUtils.format(new Date(), "yyyy-MM/dd") + "/" + fileName;
        try {
            PutObjectArgs objectArgs = PutObjectArgs.builder()
                    .bucket(minioConfig.getBucketName())
                    .object(objectName)
                    .stream(file.getInputStream(), file.getSize(), -1)
                    .contentType(file.getContentType())
                    .build();
            // 文件名称相同会覆盖
            minioClient.putObject(objectArgs);
        } catch (Exception e) {
            // 异常处理
            return null;
        }
        // 拼接minio的端点和存储桶的名称
        return getPrefix() + objectName;
    }

    /**
     * 根据文件名称删除文件
     *
     * @param file 文件路径
     */
    public void deleteFile(String file) {
        // 获取文件的名称
        String fileName = removeMinioEndpoint(file);
        try {
            minioClient.removeObject(
                    RemoveObjectArgs.builder()
                            .bucket(minioConfig.getBucketName())
                            .object(fileName)
                            .build());
        } catch (Exception e) {
            // 异常处理
            e.printStackTrace();
        }
    }

    /**
     * 下载文件
     *
     * @param file 文件路径
     */
    public void downloadFile(String file, HttpServletResponse response) {
        // 获取文件的名称
        String fileName = removeMinioEndpoint(file);
        try {
            InputStream fileIo = minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(minioConfig.getBucketName())
                            .object(fileName)
                            .build());

            // 设置响应类型
            response.setContentType("application/octet-stream");
            // 设置附件名称
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            IOUtils.copy(fileIo, response.getOutputStream());
            response.flushBuffer();

        } catch (Exception e) {
            // 异常处理
            e.printStackTrace();
        }
    }

    /**
     * 移除文件路径的前缀，获取文件的名称
     *
     * @param file 文件路径
     */
    public String removeMinioEndpoint(String file) {
        return file.substring(getPrefix().length()-1);
    }

    /**
     * 获取文件前缀
     */
    public String getPrefix() {
        return minioConfig.getEndpoint() + "/" + minioConfig.getBucketName() + "/";
    }

}