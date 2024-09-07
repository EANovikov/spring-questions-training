package com.xevgnov.unit.testing.service;

import com.xevgnov.unit.testing.dto.ExchangeStatistics;

public interface StatisticsService {
        ExchangeStatistics getStatistics(String currencySell, String currencyBuy);
}
