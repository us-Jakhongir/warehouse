package com.example.warehouse.entity;


import com.example.warehouse.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class Category extends AbsEntity {

    @Column(nullable = false)
    private String name;

    @ManyToOne
    private Category parent;

    @Column(nullable = false)
    private Boolean active;

    public Category(String name, Category parent, Boolean active) {
        this.name = name;
        this.parent = parent;
        this.active = active;
    }
}
