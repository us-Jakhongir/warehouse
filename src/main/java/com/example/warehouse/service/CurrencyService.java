package com.example.warehouse.service;

import com.example.warehouse.dto.CurrencyCreateDto;
import com.example.warehouse.entity.Currency;

import java.util.List;

public interface CurrencyService {
    Currency saveCurrency(CurrencyCreateDto dto);

    List<Currency> findAllCurrencies();

    Currency findById(Long currencyId);

    Currency updateCurrency(Long currencyId, CurrencyCreateDto dto);

    void removeCurrency(Long currencyId);
}
