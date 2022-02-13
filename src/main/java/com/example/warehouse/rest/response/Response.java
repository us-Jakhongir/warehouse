package com.example.warehouse.rest.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private boolean status;
    private String message;
    private Object data;
    private List<Object> dataList;
    private HttpStatus httpStatus;

    public Response(boolean status, String message, Object data, HttpStatus httpStatus) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.httpStatus = httpStatus;
    }

    public Response(boolean status, String message, HttpStatus httpStatus) {
        this.status = status;
        this.message = message;
        this.httpStatus = httpStatus;
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
