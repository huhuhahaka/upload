package com.fruits.upload.controller;

import com.fruits.upload.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class UploadController {

    @Autowired
    private UploadService uploadService;


    @PostMapping("/singleFileUpload")
    public String singleFileUpload(@RequestParam("file") MultipartFile uploadFile) {
        return uploadService.singleFileUpload(uploadFile);
    }


    @PostMapping("/multiFileUpload")
    public String multiFileUpload(HttpServletRequest request) {
        List<MultipartFile> uploadFiles = ((MultipartHttpServletRequest) request).getFiles("file");
        uploadFiles.forEach(uploadFile -> {
            uploadService.singleFileUpload(uploadFile);
        });

        return "上传成功！";
    }


}
