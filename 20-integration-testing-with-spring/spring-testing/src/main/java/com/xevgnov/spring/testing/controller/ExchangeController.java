package com.xevgnov.spring.testing.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.xevgnov.spring.testing.dto.ExchangeStatistics;
import com.xevgnov.spring.testing.service.StatisticsService;

import jakarta.validation.constraints.Pattern;

@Validated
@RestController
public class ExchangeController {
    
    private final StatisticsService statisticsService;

    public ExchangeController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/{currencySell}/to/{currencyBuy}")
    public ExchangeStatistics getStatistics(
        @PathVariable @Pattern(regexp = "[A-Z]{3}", 
           message = "Currency code must contain 3 upper-cased caracters") String currencySell, 
        @PathVariable @Pattern(regexp = "[A-Z]{3}", 
           message = "Currency code must contain 3 upper-cased caracters") String currencyBuy){
        return statisticsService.getStatistics(currencySell, currencyBuy);
    }
}
