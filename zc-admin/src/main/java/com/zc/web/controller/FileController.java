package com.zc.web.controller;


import com.zc.web.util.CommonResult;
import com.zc.web.util.MinioUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import static com.zc.web.util.CommonResult.success;

@RestController
@RequestMapping("/system/file")
public class FileController {

    @Resource
    MinioUtils minioUtil;

    /**
     * 上传文件
     * @param file 文件
     */
    @PostMapping("/upload")
    public CommonResult<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String objectName = minioUtil.uploadFile(file);
        return success(objectName);
    }

    /**
     * 删除文件
     * @param file 文件
     */
    @DeleteMapping("/delete")
    public CommonResult<String> deleteFile(String file) {
        minioUtil.deleteFile(file);
        return success("success");
    }

    /**
     * 下载文件
     * @param file 文件
     */
    @GetMapping("/download")
    public void downloadFile(String file, HttpServletResponse response) {
        minioUtil.downloadFile(file,response);
    }
}