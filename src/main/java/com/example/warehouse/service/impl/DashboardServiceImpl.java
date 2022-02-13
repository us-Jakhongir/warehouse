package com.example.warehouse.service.impl;

import com.example.warehouse.dto.InputOutputDto;
import com.example.warehouse.entity.InputOutput;
import com.example.warehouse.entity.InputOutputProduct;
import com.example.warehouse.enums.InputOutputType;
import com.example.warehouse.repository.InputOutputProductRepository;
import com.example.warehouse.repository.InputOutputRepository;
import com.example.warehouse.rest.response.Response;
import com.example.warehouse.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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


        return new Response(true, "Income Products by date range", result);
    }
}
