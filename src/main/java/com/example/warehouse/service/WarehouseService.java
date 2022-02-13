package com.example.warehouse.service;

import com.example.warehouse.dto.WarehouseCreateDto;
import com.example.warehouse.entity.Warehouse;

import java.util.List;

public interface WarehouseService {
    Warehouse save(WarehouseCreateDto dto);

    List<Warehouse> findAll();

    Warehouse findById(Long warehouseId);

    Warehouse updateWarehouse(Long warehouseId, WarehouseCreateDto dto);

    void remove(Long warehouseId);
}
