package com.example.warehouse.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputOutputProductCreateDto {
    private Long productId;
    private Long inputOutputId;
    private Double amount;
    private Double price;
    private LocalDate expiredDate;

}
