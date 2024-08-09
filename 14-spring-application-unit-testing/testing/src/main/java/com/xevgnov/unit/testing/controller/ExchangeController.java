package com.xevgnov.unit.testing.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.xevgnov.unit.testing.dto.ExchangeStatistics;
import com.xevgnov.unit.testing.service.CurrencyService;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Validated
@RestController
public class ExchangeController {
    
    private final CurrencyService currencyService;

    public ExchangeController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/{currencySell}/to/{currencyBuy}")
    public ExchangeStatistics getAdvice(
        @PathVariable String currencySell, 
        @PathVariable String currencyBuy){
        return currencyService.getStatistics(currencySell, currencyBuy);
    }
}
