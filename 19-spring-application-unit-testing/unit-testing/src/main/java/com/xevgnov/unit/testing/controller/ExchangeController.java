package com.xevgnov.unit.testing.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.xevgnov.unit.testing.dto.ExchangeStatistics;
import com.xevgnov.unit.testing.service.StatisticsService;

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
            @PathVariable @Pattern(regexp = "[A-Z]{3}", message = "Currency code must contain 3 upper-cased characters") String currencySell,
            @PathVariable @Pattern(regexp = "[A-Z]{3}", message = "Currency code must contain 3 upper-cased characters") String currencyBuy) {
        return statisticsService.getStatistics(currencySell, currencyBuy);
    }
}
