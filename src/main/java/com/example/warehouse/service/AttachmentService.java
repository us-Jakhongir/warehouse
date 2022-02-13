package com.example.warehouse.service;


import com.example.warehouse.entity.Attachment;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;

public interface AttachmentService {
    Attachment uploadFileToDB(MultipartFile multipartFile);

    Attachment uploadFileToRemoteStorage(MultipartFile multipartFile);

    Resource downloadFile(String fileName) throws MalformedURLException;
}
