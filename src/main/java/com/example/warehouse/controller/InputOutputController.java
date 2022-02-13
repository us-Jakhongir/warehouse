package com.example.warehouse.controller;

import com.example.warehouse.dto.InputOutputDto;
import com.example.warehouse.rest.response.Response;
import com.example.warehouse.service.InputOutputService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/input_output")
@RequiredArgsConstructor
public class InputOutputController {
    private final InputOutputService inputOutputService;

    @PostMapping
    public Response save(@RequestBody InputOutputDto dto) {
        Response response = inputOutputService.save(dto);
        return response;
    }


    @PostMapping("/{input_output_id}/details")
    public Response saveDetail(@PathVariable("input_output_id") Long id,
                               @RequestBody List<InputOutputDto.DetailDto> list) {
        Response response = inputOutputService.saveDetail(id, list);
        return response;
    }
}
