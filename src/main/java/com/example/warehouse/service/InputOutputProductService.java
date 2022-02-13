package com.example.warehouse.service;

import com.example.warehouse.dto.InputOutputProductCreateDto;
import com.example.warehouse.entity.InputOutputProduct;

import java.util.List;

public interface InputOutputProductService {
    InputOutputProduct save(InputOutputProductCreateDto dto);

    List<InputOutputProduct> findAll();

}
