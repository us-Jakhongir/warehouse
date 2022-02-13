package com.example.warehouse.service.impl;

import com.example.warehouse.dto.CurrencyCreateDto;
import com.example.warehouse.entity.Currency;
import com.example.warehouse.exception.NotFoundException;
import com.example.warehouse.repository.CurrencyRepository;
import com.example.warehouse.service.CurrencyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;

    public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }


    @Override
    public Currency saveCurrency(CurrencyCreateDto dto) {
        Currency currency = new Currency(dto.getName());

        return currencyRepository.save(currency);
    }

    @Override
    public List<Currency> findAllCurrencies() {
        return currencyRepository.findAll();
    }

    @Override
    public Currency findById(Long currencyId) {
        return currencyRepository.findById(currencyId)
                .orElseThrow(() -> new NotFoundException("Currency Not Found"));
    }

    @Override
    public Currency updateCurrency(Long currencyId, CurrencyCreateDto dto) {
        Optional<Currency> currencyOptional = currencyRepository.findById(currencyId);
        if (currencyOptional.isEmpty())
            throw new NotFoundException("Currency Not Found");
        Currency currency = currencyOptional.get();

        if (!currency.getName().equals(dto.getName())) {
            currency.setName(dto.getName());
        }
        return currencyRepository.save(currency);
    }

    @Override
    public void removeCurrency(Long currencyId) {
        currencyRepository.deleteById(currencyId);
    }
}
