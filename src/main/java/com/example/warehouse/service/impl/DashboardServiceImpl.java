package com.example.warehouse.service.impl;

import com.example.warehouse.dto.InputOutputDto;
import com.example.warehouse.dto.ProductDto;
import com.example.warehouse.entity.InputOutput;
import com.example.warehouse.entity.InputOutputProduct;
import com.example.warehouse.enums.InputOutputType;
import com.example.warehouse.repository.InputOutputProductRepository;
import com.example.warehouse.repository.InputOutputRepository;
import com.example.warehouse.rest.response.Response;
import com.example.warehouse.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final InputOutputRepository inputOutputRepository;
    private final InputOutputProductRepository inputOutputProductRepository;

    @Override
    public Response getIncomeByDateRange(LocalDateTime start, LocalDateTime end, InputOutputType type) {

        List<InputOutput> list = inputOutputRepository.selectIncomeByDateRange(start, end, type);
        List<InputOutputDto.GetDto> result = getIncomes(list);
        if (result.size() > 0)
            return new Response(true, "Income Products by date range", result, HttpStatus.OK);
        return new Response(false, "Not Found", HttpStatus.NOT_FOUND);
    }


    @Override
    public Response getTop10InputOutputByDateRange(LocalDateTime start, LocalDateTime end, InputOutputType type) {

        List<InputOutput> list = inputOutputRepository.selectInputOutputByDateRange(start, end, type);
        List<InputOutputDto.GetDto> result = getIncomes(list);
        return new Response(true, "Top 10 Products by date range", result, HttpStatus.OK);
    }

    private List<InputOutputDto.GetDto> getIncomes(List<InputOutput> list) {
        List<InputOutputDto.GetDto> result = new ArrayList<>();

        for (InputOutput inputOutput : list) {
            List<InputOutputProduct> details = inputOutputProductRepository.findAllByInputOutputId(inputOutput.getId());
            InputOutputDto.GetDto dto = new InputOutputDto.GetDto(
                    inputOutput.getDate(),
                    inputOutput.getWarehouse(),
                    inputOutput.getSupplier(),
                    inputOutput.getCurrency(),
                    inputOutput.getCode(),
                    inputOutput.getType(),
                    details
            );
            result.add(dto);
        }
        return result;
    }

    @Override
    public Response getAllExpireDateSoonProducts() {
        List<ProductDto> list = inputOutputProductRepository.selectAllExpireDateSoon(LocalDateTime.now().plus(1, ChronoUnit.WEEKS));


        return new Response(true, "Product List will expire after week", list, HttpStatus.OK);
    }
}
