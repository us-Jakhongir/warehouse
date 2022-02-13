package com.example.warehouse.service.impl;

import com.example.warehouse.entity.Attachment;
import com.example.warehouse.exception.BadRequestException;
import com.example.warehouse.exception.FileStorageException;
import com.example.warehouse.exception.NotFoundException;
import com.example.warehouse.properties.StorageProperties;
import com.example.warehouse.repository.AttachmentRepository;
import com.example.warehouse.service.AttachmentService;
import lombok.SneakyThrows;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class AttachmentServiceImpl implements AttachmentService {

    private final AttachmentRepository attachmentRepository;
    private final Path fileLocation;

    public AttachmentServiceImpl(StorageProperties storageProperties, AttachmentRepository attachmentRepository) {
        this.attachmentRepository = attachmentRepository;
        this.fileLocation = Paths.get(storageProperties.getUploadDir()).toAbsolutePath().normalize();
    }

    @SneakyThrows
    @Override
    public Attachment uploadFileToDB(MultipartFile multipartFile) {
        String originalFilename = multipartFile.getOriginalFilename();
        if (originalFilename.contains(".."))
            throw new FileStorageException("Uzr, fayl nomida xatolik bor " + originalFilename);

        String uniqueFileName = System.currentTimeMillis() + originalFilename;

        Attachment attachment = new Attachment(
                uniqueFileName,
                originalFilename,
                multipartFile.getSize(),
                multipartFile.getContentType(),
                true,
                multipartFile.getBytes()

        );

        return attachmentRepository.save(attachment);

    }

    @SneakyThrows
    @Override
    public Attachment uploadFileToRemoteStorage(MultipartFile multipartFile) {
        String originalFilename = multipartFile.getOriginalFilename();

        if (originalFilename.contains(".."))
            throw new BadRequestException("Uzr, faylda xatolik bor " + originalFilename);

        Path path = this.fileLocation.resolve(originalFilename);

        // FILE SYSTEMAGA NUSXALANDI
        Files.copy(multipartFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

        Attachment attachment = new Attachment(
                System.currentTimeMillis() + originalFilename,
                originalFilename,
                multipartFile.getSize(),
                multipartFile.getContentType(),
                false,
                path.toString());

        return attachmentRepository.save(attachment);
    }

    @Override
    public Resource downloadFile(String fileName) throws MalformedURLException {
        Path path = this.fileLocation.resolve(fileName);
        Resource resource = new UrlResource(path.toUri());
        if (resource.exists()) {
            return resource;
        }

        throw new NotFoundException("File Not Found");
    }
}
