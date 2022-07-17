package com.it.revolution.customer.service.app.amazon.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService  {

    String uploadFile(MultipartFile multipartFile);

    void deleteFile(String fileUrl);
}
