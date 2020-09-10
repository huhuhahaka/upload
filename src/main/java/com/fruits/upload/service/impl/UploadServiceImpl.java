package com.fruits.upload.service.impl;

import com.fruits.upload.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Service
public class UploadServiceImpl implements UploadService {

    @Value("${upload.directory}")
    private String uploadDirectory;

    private final String USER_DIR = System.getProperty("user.dir");

    @Override
    public String singleFileUpload(MultipartFile uploadFile) {
        if (uploadFile.isEmpty()) {
            log.info("上传失败，请选择文件！");
            return "上传失败，请选择文件！";
        }

        String destFilePath = USER_DIR + uploadDirectory;
        File fileDir = new File(destFilePath);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
            if (fileDir.isDirectory()) {
                log.info("创建的文件路径为：{}", destFilePath);
            } else {
                log.info("后台上传路径 配置异常！");
                return "后台上传路径 配置异常！";
            }
        }

        String fileName = uploadFile.getOriginalFilename();
        String fileNamePrefix = UUID.randomUUID().toString().replaceAll("-", "") + "_";
        File destFile = new File(destFilePath + File.separator + fileNamePrefix + fileName);

        try {
            uploadFile.transferTo(destFile);
            log.info("{} 上传成功", fileName);
            return fileName + "上传成功！";
        } catch (IOException e) {
            log.error(e.toString(), e);
        }

        return "上传失败！";
    }

}
