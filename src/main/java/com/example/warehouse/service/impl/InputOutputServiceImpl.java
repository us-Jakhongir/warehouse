package com.example.warehouse.service.impl;

import com.example.warehouse.dto.InputOutputDto;
import com.example.warehouse.entity.InputOutput;
import com.example.warehouse.entity.InputOutputProduct;
import com.example.warehouse.enums.InputOutputType;
import com.example.warehouse.exception.NotFoundException;
import com.example.warehouse.repository.*;
import com.example.warehouse.rest.response.Response;
import com.example.warehouse.service.InputOutputService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InputOutputServiceImpl implements InputOutputService {

    private final InputOutputRepository inputOutputRepository;
    private final InputOutputProductRepository inputOutputProductRepository;
    private final WarehouseRepository warehouseRepository;
    private final UserRepository supplierRepository;
    private final CurrencyRepository currencyRepository;
    private final ProductRepository productRepository;


    @Override
    public Response save(InputOutputDto dto) {

        InputOutput inputOutput = new InputOutput(
                LocalDateTime.now(),
                warehouseRepository.findById(dto.getWarehouseId()).orElseThrow(() -> new NotFoundException("Warehouse Not Found")),
                supplierRepository.findById(dto.getSupplierId()).orElseThrow(() -> new NotFoundException("User Not Found")),
                currencyRepository.findById(dto.getCurrencyId()).orElseThrow(() -> new NotFoundException("Currency Not Found")),
                dto.getFactureNumber(),
                UUID.randomUUID(),
                dto.getType()
        );

        InputOutput saveInputOutput = inputOutputRepository.save(inputOutput);
        InputOutputType type = dto.getType();
        String message;
        if (type == InputOutputType.INPUT)
            message = "Input";
        else
            message = "Output";

        return new Response(true, message + " Saved!", saveInputOutput, HttpStatus.CREATED);
    }

    @Override
    public Response saveDetail(Long id, List<InputOutputDto.DetailDto> list) {
        InputOutput inputOutput = inputOutputRepository.findById(id).orElseThrow(() -> new NotFoundException("Input/Output Not Found"));
        Set<InputOutputProduct> setProducts = new HashSet<>();

        for (InputOutputDto.DetailDto dto : list) {
            InputOutputProduct inputOutputProduct = new InputOutputProduct(
                    productRepository.findById(dto.getProductId()).orElseThrow(() -> new NotFoundException("Product Not Found")),
                    inputOutput,
                    dto.getAmount(),
                    dto.getPrice(),
                    dto.getExpiredDate()
            );
            setProducts.add(inputOutputProduct);
        }

        if (setProducts.size() > 0)
            inputOutputProductRepository.saveAll(setProducts);


        return new Response(true, "Success", HttpStatus.CREATED);
    }
}
