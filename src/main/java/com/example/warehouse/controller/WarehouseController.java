package com.example.warehouse.controller;

import com.example.warehouse.dto.WarehouseCreateDto;
import com.example.warehouse.entity.Warehouse;
import com.example.warehouse.rest.response.Response;
import com.example.warehouse.service.WarehouseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/warehouse")
public class WarehouseController {

    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @PostMapping
    public Warehouse save(@RequestBody WarehouseCreateDto dto) {
        Warehouse warehouse = warehouseService.save(dto);
        return warehouse;
    }

    @GetMapping
    public List<Warehouse> findAll() {
        List<Warehouse> warehouseList = warehouseService.findAll();
        return warehouseList;
    }

    @GetMapping("/{id}")
    public Warehouse findById(@PathVariable("id") Long warehouseId) {
        Warehouse warehouse = warehouseService.findById(warehouseId);
        return warehouse;
    }

    @PutMapping("/{id}")
    public Warehouse edit(@PathVariable("id") Long warehouseId, @RequestBody WarehouseCreateDto dto) {
        return warehouseService.updateWarehouse(warehouseId, dto);
    }

    @DeleteMapping("/{id}")
    public Response remove(@PathVariable("id") Long warehouseId) {
        warehouseService.remove(warehouseId);
        return new Response("Successfully deleted");
    }

}
