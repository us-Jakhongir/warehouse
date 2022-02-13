package com.example.warehouse.controller;


import com.example.warehouse.dto.MeasurementCreateDto;
import com.example.warehouse.entity.Measurement;
import com.example.warehouse.rest.response.Response;
import com.example.warehouse.service.MeasurementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/measurements")
public class MeasurementController {

    private final MeasurementService measurementService;


    @PostMapping
    public Measurement save(@RequestBody MeasurementCreateDto dto) {
        Measurement measurement = measurementService.save(dto);
        return measurement;
    }

    @GetMapping
    public List<Measurement> findAll() {
        return measurementService.findAll();
    }

    @GetMapping("/{measurement_id}")
    public Measurement findById(@PathVariable("measurement_id") Long measurementId) {
        return measurementService.findById(measurementId);
    }


    @PutMapping("/{measurement_id}")
    public Measurement edit(@PathVariable("measurement_id") Long measurementId, @RequestBody MeasurementCreateDto dto) {
        return measurementService.updateMeasurement(measurementId, dto);
    }

    @DeleteMapping("/{measurement_id}")
    public Response remove(@PathVariable("measurement_id") Long measurementId) {
        measurementService.delete(measurementId);
        return new Response("Successfully Deleted: ");
    }

}
