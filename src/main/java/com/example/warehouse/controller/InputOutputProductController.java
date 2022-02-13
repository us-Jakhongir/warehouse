package com.example.warehouse.controller;


import com.example.warehouse.dto.InputOutputProductCreateDto;
import com.example.warehouse.entity.InputOutputProduct;
import com.example.warehouse.service.InputOutputProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class InputOutputProductController {

    private final InputOutputProductService inputOutputProductService;


    @PostMapping
    public InputOutputProduct save(@RequestBody InputOutputProductCreateDto dto) {
        InputOutputProduct inputOutputProduct = inputOutputProductService.save(dto);
        return inputOutputProduct;
    }

    @GetMapping
    public List<InputOutputProduct> findAll() {
       return inputOutputProductService.findAll();
    }

}
