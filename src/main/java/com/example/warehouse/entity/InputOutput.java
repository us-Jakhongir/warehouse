package com.example.warehouse.entity;


import com.example.warehouse.entity.template.AbsEntity;
import com.example.warehouse.enums.InputOutputType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class InputOutput extends AbsEntity {
    @Column(nullable = false)
    private LocalDateTime date;

    @ManyToOne
    private Warehouse warehouse;

    @ManyToOne
    private User supplier;

    @ManyToOne
    private Currency currency;

    @Column(nullable = false)
    private String facture_number;

    @Column(nullable = false)
    private UUID code;

    @Enumerated(EnumType.STRING)
    private InputOutputType type = InputOutputType.INPUT;

    public InputOutput(LocalDateTime date, Warehouse warehouse, User supplier, Currency currency, String facture_number, UUID code, InputOutputType type) {
        this.date = date;
        this.warehouse = warehouse;
        this.supplier = supplier;
        this.currency = currency;
        this.facture_number = facture_number;
        this.code = code;
        this.type = type;
    }
}
