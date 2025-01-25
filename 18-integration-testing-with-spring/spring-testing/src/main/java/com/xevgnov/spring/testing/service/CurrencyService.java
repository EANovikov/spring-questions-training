package com.xevgnov.spring.testing.service;

import com.xevgnov.spring.testing.dto.FxRatesResponse;

public interface CurrencyService {
    FxRatesResponse getFxRateForDate(String date, String currencyBuy, String currencySell);
}
