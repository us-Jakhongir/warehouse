package com.example.warehouse.controller;


import com.example.warehouse.entity.InputOutput;
import com.example.warehouse.enums.InputOutputType;
import com.example.warehouse.rest.response.Response;
import com.example.warehouse.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@RestController
@RequestMapping("/api/v1/dashboards")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;


    @GetMapping("/income_date_range")
    public Response getIncomeByDateRange(@RequestParam(value = "start_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate startDate,
                                         @RequestParam("end_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                         @RequestParam("type")InputOutputType type) {
        LocalDateTime start = startDate.atStartOfDay();
        LocalDateTime end = endDate.atStartOfDay().plus(1, ChronoUnit.DAYS).minus(1, ChronoUnit.SECONDS);
        return dashboardService.getIncomeByDateRange(start, end, type);

    }



}
