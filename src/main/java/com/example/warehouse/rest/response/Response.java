package com.example.warehouse.rest.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private boolean status;
    private String message;
    private Object data;
    private List<Object> dataList;

    public Response(boolean status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public Response(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public Response(boolean status, String message, List<Object> dataList) {
        this.status = status;
        this.message = message;
        this.dataList = dataList;
    }

    public Response(String message) {
        this.message = message;
    }
}
