package com.example.warehouse.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttachmentCreateDto {

    private String name;
    private String originalName;
    private String contentType;
    private Integer size;
    private boolean status;
    private String data;
    private String path;


}
