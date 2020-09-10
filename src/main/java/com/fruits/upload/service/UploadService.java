package com.fruits.upload.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

    String singleFileUpload(MultipartFile file);

}
