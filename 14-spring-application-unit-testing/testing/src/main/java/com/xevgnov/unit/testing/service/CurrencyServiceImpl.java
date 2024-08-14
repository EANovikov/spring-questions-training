package com.xevgnov.unit.testing.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xevgnov.unit.testing.client.FxRatesApiClient;
import com.xevgnov.unit.testing.dto.ExchangeStatistics;
import com.xevgnov.unit.testing.dto.ExchangeStatistics.ExchangeStatisticsBuilder;
import com.xevgnov.unit.testing.dto.FxRatesResponse;
import com.xevgnov.unit.testing.repository.ExchangeRecordsRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CurrencyServiceImpl implements CurrencyService {

    private final FxRatesApiClient fxRatesApiClient;
    private final ExchangeRecordsRepository exchangeRecordsRepository;
    private final ObjectMapper mapper = new ObjectMapper();
    private final  DateTimeFormatter datePattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public CurrencyServiceImpl(FxRatesApiClient fxRatesApiClient, 
                               ExchangeRecordsRepository exchangeRecordsRepository) {
        this.fxRatesApiClient = fxRatesApiClient;
        this.exchangeRecordsRepository = exchangeRecordsRepository;
    }

    public ExchangeStatistics getStatistics(String currencySell, String currencyBuy) {
        List<FxRatesResponse> responses = fetchFxRatesPerWeek(currencySell, currencyBuy);
        return ExchangeStatistics.builder()
        .buyCurrency(currencyBuy)
        .sellCurrency(currencySell)
        .date(responses.get(0).getDate().format(datePattern))
        .currentPrice(responses.get(0).getRates().get(currencyBuy))
        .middlePrice(getMiddlePrice(responses, currencyBuy))
        .priceHistory(getPriceHistory(responses, currencyBuy))
        .build();
    }

    private Map<String, String> getPriceHistory(List<FxRatesResponse> responses, String currency){
        Map<String, String> priceHistory = new LinkedHashMap<>();
        for(FxRatesResponse response : responses){
           String date = response.getDate().format(datePattern);
           String rate = String.valueOf(response.getRates().get(currency));
           priceHistory.put(date, rate);
        }
        return priceHistory;
    }

    private Double getMiddlePrice(List<FxRatesResponse> responses, String currency){
        return responses.stream()
        .mapToDouble(response -> response.getRates().get(currency))
        .average()
        .orElse(0);
    }

    private List<FxRatesResponse> fetchFxRatesPerWeek(String currencySell, String currencyBuy){
        List<FxRatesResponse> responses = new ArrayList<>();
        LocalDate today = LocalDate.now();

        for(int i = 0; i < 7; i++){
            LocalDate currentDate = today.minusDays(i);
            String formattedDate = currentDate.format(datePattern);

            FxRatesResponse result = fxRatesApiClient.getConvertationRate(formattedDate, currencyBuy, currencySell);
            responses.add(result);
        }
        return responses;
    }

}