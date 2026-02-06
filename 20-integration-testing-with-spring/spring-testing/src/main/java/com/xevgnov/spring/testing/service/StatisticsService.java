package com.xevgnov.spring.testing.service;

import com.xevgnov.spring.testing.dto.ExchangeStatistics;

public interface StatisticsService {
        ExchangeStatistics getStatistics(String currencySell, String currencyBuy);
}
