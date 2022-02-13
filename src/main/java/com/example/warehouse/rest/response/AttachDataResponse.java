package com.example.warehouse.rest.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttachDataResponse {
    private Long id;
    private String path;
    private String contentType;
    private String name;
    private boolean fromSystem;

}
