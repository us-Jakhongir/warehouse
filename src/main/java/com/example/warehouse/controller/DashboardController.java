package com.example.warehouse.controller;


import com.example.warehouse.entity.InputOutput;
import com.example.warehouse.enums.InputOutputType;
import com.example.warehouse.rest.response.Response;
import com.example.warehouse.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
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
    public HttpEntity<?> getIncomeByDateRange(@RequestParam(value = "start_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate startDate,
                                           @RequestParam("end_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                           @RequestParam("type")InputOutputType type) {
        LocalDateTime start = startDate.atStartOfDay();
        LocalDateTime end = endDate.atStartOfDay().plus(1, ChronoUnit.DAYS).minus(1, ChronoUnit.SECONDS);
        Response response = dashboardService.getIncomeByDateRange(start, end, type);
        return ResponseEntity.status(response.getHttpStatus()).body(response);

    }

    @GetMapping("/products/date_range/top")
    public Response getTop10IncomeByDateRange(@RequestParam(value = "start_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                              @RequestParam(value = "end_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                              @RequestParam("type") InputOutputType type) {
        LocalDateTime start = startDate.atStartOfDay();
        LocalDateTime end = endDate.atStartOfDay().plus(1, ChronoUnit.DAYS).minus(1, ChronoUnit.SECONDS);
        return dashboardService.getTop10InputOutputByDateRange(start, end, type);
    }


    @GetMapping("/products/expire_soon/list")
    public Response getAllExpireSoonProducts() {
        return dashboardService.getAllExpireDateSoonProducts();
    }






}
