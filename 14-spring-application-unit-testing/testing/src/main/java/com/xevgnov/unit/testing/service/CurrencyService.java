package com.xevgnov.unit.testing.service;

import com.xevgnov.unit.testing.dto.ExchangeStatistics;

public interface CurrencyService {
    ExchangeStatistics getStatistics(String currencySell, String currencyBuy);
}
