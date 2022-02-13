package com.example.warehouse.service.impl;

import com.example.warehouse.dto.InputOutputProductCreateDto;
import com.example.warehouse.entity.InputOutput;
import com.example.warehouse.entity.InputOutputProduct;
import com.example.warehouse.exception.NotFoundException;
import com.example.warehouse.repository.InputOutputProductRepository;
import com.example.warehouse.repository.InputOutputRepository;
import com.example.warehouse.repository.ProductRepository;
import com.example.warehouse.service.InputOutputProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InputOutputServiceProductImpl implements InputOutputProductService {
    private final InputOutputProductRepository inputOutputProductRepository;
    private final ProductRepository productRepository;
    private final InputOutputRepository inputOutput;


    @Override
    public InputOutputProduct save(InputOutputProductCreateDto dto) {
        InputOutputProduct inputOutputProduct = new InputOutputProduct(
                productRepository.findById(dto.getProductId()).orElseThrow(() -> new NotFoundException("Not Found Product")),
                inputOutput.findById(dto.getInputOutputId()).orElseThrow(() -> new NotFoundException("Not Found InputOutput")),
                dto.getAmount(),
                dto.getPrice(),
                dto.getExpiredDate()
        );

        return inputOutputProductRepository.save(inputOutputProduct);
    }

    @Override
    public List<InputOutputProduct> findAll() {
        return inputOutputProductRepository.findAll();
    }
}
