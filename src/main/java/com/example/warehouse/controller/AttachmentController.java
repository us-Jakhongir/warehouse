package com.example.warehouse.controller;


import com.example.warehouse.entity.Attachment;
import com.example.warehouse.rest.response.AttachDataResponse;
import com.example.warehouse.rest.response.Response;
import com.example.warehouse.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/attachment")
public class AttachmentController {
    private final AttachmentService attachmentService;


    // UPLOAD AND DOWNLOAD TO(FROM) DATABASE


    @PostMapping("/upload_file")
    public Response uploadFile(@RequestParam("file") MultipartFile multipartFile,
                               @RequestParam("status") boolean toSystem ) {

        Attachment attachment;
        if (toSystem) {
            attachment = attachmentService.uploadFileToRemoteStorage(multipartFile);
        } else {
            attachment = attachmentService.uploadFileToDB(multipartFile);
        }

        if (attachment == null)
            return new Response(false, "Something went wrong", HttpStatus.BAD_REQUEST);

        AttachDataResponse attachData = new AttachDataResponse(
                attachment.getId(),
                attachment.getPath(),
                attachment.getContentType(),
                attachment.getName(),
                toSystem
        );
        return new Response(true, "File Uploaded", attachData);
    }


    @SneakyThrows
    @GetMapping(value = "/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFileName(@PathVariable("fileName") String fileName, HttpServletRequest request) {
       Resource resource = attachmentService.downloadFile(fileName);
       String type = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());

       return ResponseEntity.ok()
                       .contentType(MediaType.parseMediaType(type))
                       .body(resource);

    }



}
