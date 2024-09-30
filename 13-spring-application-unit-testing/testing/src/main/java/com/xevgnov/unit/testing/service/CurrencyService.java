package com.xevgnov.unit.testing.service;

import com.xevgnov.unit.testing.dto.FxRatesResponse;

public interface CurrencyService {
    FxRatesResponse getFxRateForDate(String date, String currencyBuy, String currencySell);
}
