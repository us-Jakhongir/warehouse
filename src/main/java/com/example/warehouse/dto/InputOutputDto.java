package com.example.warehouse.dto;


import com.example.warehouse.entity.Currency;
import com.example.warehouse.entity.InputOutputProduct;
import com.example.warehouse.entity.User;
import com.example.warehouse.entity.Warehouse;
import com.example.warehouse.enums.InputOutputType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputOutputDto {
    private LocalDateTime date;
    private Long warehouseId;
    private Long supplierId;
    private Long currencyId;
    private String factureNumber;
    private InputOutputType type;


    //INNER CLASS

    @Data
    public static class DetailDto {
        private Long productId;
        private Double amount;
        private Double price;
        private LocalDateTime expiredDate;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GetDto {
        private LocalDateTime date;
        private Warehouse warehouse;
        private User supplier;
        private Currency currency;
        private UUID code;
        private InputOutputType type;
        private List<InputOutputProduct> details;
    }


}
