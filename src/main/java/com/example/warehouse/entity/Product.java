package com.example.warehouse.entity;


import com.example.warehouse.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product extends AbsEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Measurement measurement;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Attachment> attachments = new ArrayList<>();

    @Column(nullable = false)
    private UUID code;



}
