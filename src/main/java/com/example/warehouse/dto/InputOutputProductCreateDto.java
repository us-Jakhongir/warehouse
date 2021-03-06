package com.example.warehouse.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputOutputProductCreateDto {
    private Long productId;
    private Long inputOutputId;
    private Double amount;
    private Double price;
    private LocalDateTime expiredDate;

}
