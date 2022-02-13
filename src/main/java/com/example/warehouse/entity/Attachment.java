package com.example.warehouse.entity;


import com.example.warehouse.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attachment extends AbsEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String originalName;

    @Column(nullable = false)
    private long size;

    @Column(nullable = false)
    private String contentType;

    /**
     * TRUE - UPLOAD TO DB, FALSE - FILE SYSTEM;
     */
    @Column(nullable = false)
    private Boolean status;

    @Column
    private byte[] data;

    @Column
    private String path;



    public Attachment(String name, String originalName, long size, String contentType, Boolean status, byte[] data) {

        this.name = name;
        this.originalName = originalName;
        this.size = size;
        this.contentType = contentType;
        this.status = status;
        this.data = data;
    }


    public Attachment(String name, String originalName, long size, String contentType, Boolean status, String path) {

        this.name = name;
        this.originalName = originalName;
        this.size = size;
        this.contentType = contentType;
        this.status = status;
        this.path = path;
    }

}
