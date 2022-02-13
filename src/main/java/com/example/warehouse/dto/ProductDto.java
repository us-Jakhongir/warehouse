package com.example.warehouse.dto;


import com.example.warehouse.entity.InputOutput;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long productId;
    private String productName;
    private LocalDateTime expireDate;
    private Double amount;
    private Double price;
    private InputOutput inputOutput;
}
