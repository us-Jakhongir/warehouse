package com.example.warehouse.service;

import com.example.warehouse.enums.InputOutputType;
import com.example.warehouse.rest.response.Response;

import java.time.LocalDateTime;

public interface DashboardService {
    Response getIncomeByDateRange(LocalDateTime start, LocalDateTime end, InputOutputType type);
}
