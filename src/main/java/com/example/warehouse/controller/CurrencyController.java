package com.example.warehouse.controller;


import com.example.warehouse.dto.CurrencyCreateDto;
import com.example.warehouse.entity.Currency;
import com.example.warehouse.rest.response.Response;
import com.example.warehouse.service.CurrencyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/currencies")
public class CurrencyController {

    private final CurrencyService currencyService;


    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }


    @PostMapping
    public Currency save(@RequestBody CurrencyCreateDto dto) {
        Currency currency = currencyService.saveCurrency(dto);
        return currency;
    }

    @GetMapping
    public List<Currency> findAll() {
        List<Currency> currencyList = currencyService.findAllCurrencies();
        return currencyList;
    }

    @GetMapping("/{currency_id}")
    public Currency findById(@PathVariable("currency_id") Long currencyId) {
        return currencyService.findById(currencyId);
    }

    @PutMapping("/{currency_id}")
    public Currency edit(@PathVariable("currency_id") Long currencyId, @RequestBody CurrencyCreateDto dto) {
        return currencyService.updateCurrency(currencyId, dto);
    }

    @DeleteMapping("/{currency_id}")
    public Response removeCurrency(@PathVariable("currency_id") Long currencyId) {
        currencyService.removeCurrency(currencyId);
        return new Response("Successfully Deleted");
    }

}
