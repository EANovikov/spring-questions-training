package com.xevgnov.unit.testing.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.xevgnov.unit.testing.dto.ExchangeStatistics;
import com.xevgnov.unit.testing.dto.FxRatesResponse;
import com.xevgnov.unit.testing.exception.StatisticsServiceException;

import lombok.NonNull;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    private final DateTimeFormatter datePattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final CurrencyService currencyService;

    public StatisticsServiceImpl(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @Override
    public ExchangeStatistics getStatistics(@NonNull String currencySell, @NonNull String currencyBuy) {
        try {
            Map<String, FxRatesResponse> responses = fetchFxRatesPerWeek(currencySell, currencyBuy);
            var currentDateResponse = responses.entrySet().stream().findFirst().orElseGet(null);
            return ExchangeStatistics.builder()
                    .buyCurrency(currencyBuy)
                    .sellCurrency(currencySell)
                    .date(currentDateResponse.getKey())
                    .currentPrice(currentDateResponse.getValue().getRates().get(currencyBuy))
                    .averagePrice(getAveragePrice(responses.values(), currencyBuy))
                    .priceHistory(getPriceHistory(responses, currencyBuy))
                    .build();
        } catch (Throwable e) {
            throw new StatisticsServiceException(String.format(
                    "Failed to collect statistics for the pair: %s/%s due to: %s",
                    currencySell, currencyBuy, e.getMessage()), e);
        }
    }

    private Map<String, Double> getPriceHistory(Map<String, FxRatesResponse> responses, String currency) {
        Map<String, Double> priceHistory = new LinkedHashMap<>();
        responses.entrySet()
                .forEach(entry -> priceHistory.put(entry.getKey(), entry.getValue().getRates().get(currency)));
        return priceHistory;
    }

    private Double getAveragePrice(Collection<FxRatesResponse> responses, String currency) {
        return responses.stream()
                .mapToDouble(response -> response.getRates().get(currency).doubleValue())
                .average()
                .orElse(0);
    }

    private Map<String, FxRatesResponse> fetchFxRatesPerWeek(String currencySell, String currencyBuy) {
        LocalDate currentDate = LocalDate.now();
        Map<String, FxRatesResponse> responses = new LinkedHashMap<>();
        while (responses.size() < 7) {
            String formattedDate = currentDate.format(datePattern);
            FxRatesResponse result = currencyService.getFxRateForDate(formattedDate, currencyBuy, currencySell);
            responses.putIfAbsent(formattedDate, result);
            currentDate = currentDate.minusDays(1);
        }
        return responses;
    }

}
