package com.example.warehouse.service;

import com.example.warehouse.dto.MeasurementCreateDto;
import com.example.warehouse.entity.Measurement;
import com.example.warehouse.rest.response.Response;

import java.util.List;

public interface MeasurementService {
    Measurement save(MeasurementCreateDto dto);

    List<Measurement> findAll();

    Measurement findById(Long measurementId);

    Measurement updateMeasurement(Long measurementId, MeasurementCreateDto dto);

    Response delete(Long measurementId);
}
