package com.example.warehouse.service.impl;

import com.example.warehouse.dto.WarehouseCreateDto;
import com.example.warehouse.entity.Warehouse;
import com.example.warehouse.exception.NotFoundException;
import com.example.warehouse.repository.WarehouseRepository;
import com.example.warehouse.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepository warehouseRepository;


    @Override
    public Warehouse save(WarehouseCreateDto dto) {
        Warehouse warehouse = new Warehouse(
                dto.getName(),
                dto.isActive()
        );
        return warehouseRepository.save(warehouse);
    }

    @Override
    public List<Warehouse> findAll() {
        List<Warehouse> warehouseList = warehouseRepository.findAll();
        return warehouseList;
    }

    @Override
    public Warehouse findById(Long warehouseId) {
        Optional<Warehouse> warehouseOptional = warehouseRepository.findById(warehouseId);
        if (warehouseOptional.isEmpty())
            throw new NotFoundException("Warehouse Not Found");
        return warehouseOptional.get();
    }

    @Override
    public Warehouse updateWarehouse(Long warehouseId, WarehouseCreateDto dto) {
        Optional<Warehouse> warehouseOptional = warehouseRepository.findById(warehouseId);
        if (warehouseOptional.isEmpty())
            throw new NotFoundException("Warehouse Not Found");
        Warehouse warehouse = warehouseOptional.get();

        if (!warehouse.getName().equals(dto.getName())) {
            warehouse.setName(dto.getName());
        }

        warehouse.setActive(dto.isActive());

        return warehouseRepository.save(warehouse);
    }

    @Override
    public void remove(Long warehouseId) {
        warehouseRepository.deleteById(warehouseId);
    }
}


