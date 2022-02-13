package com.example.warehouse.service.impl;

import com.example.warehouse.dto.MeasurementCreateDto;
import com.example.warehouse.entity.Measurement;
import com.example.warehouse.exception.NotFoundException;
import com.example.warehouse.repository.MeasurementRepository;
import com.example.warehouse.rest.response.Response;
import com.example.warehouse.service.MeasurementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MeasurementServiceImpl implements MeasurementService {

    private final MeasurementRepository measurementRepository;


    @Override
    public Measurement save(MeasurementCreateDto dto) {
        Measurement measurement = new Measurement(
                dto.getName()
        );
        return measurementRepository.save(measurement);
    }

    @Override
    public List<Measurement> findAll() {
        return measurementRepository.findAll();
    }

    @Override
    public Measurement findById(Long measurementId) {
        return measurementRepository.findById(measurementId)
                .orElseThrow(() -> new NotFoundException("Not Found Measurement"));
    }


    @Override
    public Measurement updateMeasurement(Long measurementId, MeasurementCreateDto dto) {
        Measurement measurement = measurementRepository.findById(measurementId)
                .orElseThrow(() -> new NotFoundException("Measurement Not Found"));

        if (!measurement.getName().equals(dto.getName())) {
            measurement.setName(dto.getName());
        }
        return measurement;
    }

    @Override
    public Response delete(Long measurementId) {
         measurementRepository.deleteById(measurementId);
         return null;
    }
}

