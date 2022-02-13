package com.example.warehouse.service;

import com.example.warehouse.dto.InputOutputDto;
import com.example.warehouse.rest.response.Response;

import java.util.List;

public interface InputOutputService {
    Response save(InputOutputDto dto);

    Response saveDetail(Long id, List<InputOutputDto.DetailDto> list);
}
