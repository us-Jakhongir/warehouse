package com.example.warehouse.service;

import com.example.warehouse.enums.InputOutputType;
import com.example.warehouse.rest.response.Response;

import java.time.LocalDateTime;

public interface DashboardService {

    /**
     * KUNLIK KIRIM BO'LGAN MAXSULOTLAR
     * @param start
     * @param end
     * @param type
     * @return
     */
    Response getIncomeByDateRange(LocalDateTime start, LocalDateTime end, InputOutputType type);

    Response getTop10InputOutputByDateRange(LocalDateTime start, LocalDateTime end, InputOutputType type);

    Response getAllExpireDateSoonProducts();

}
