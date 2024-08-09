package com.xevgnov.unit.testing.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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

    public CurrencyServiceImpl(FxRatesApiClient fxRatesApiClient, 
                               ExchangeRecordsRepository exchangeRecordsRepository) {
        this.fxRatesApiClient = fxRatesApiClient;
        this.exchangeRecordsRepository = exchangeRecordsRepository;
    }

    public ExchangeStatistics getStatistics(String currencySell, String currencyBuy) {
        List<FxRatesResponse> responses = fetchFxRatesPerWeek(currencySell, currencyBuy);
        ExchangeStatisticsBuilder statistics = ExchangeStatistics.builder();
        //todo add stats
        return statistics.build();
    }

    private List<FxRatesResponse> fetchFxRatesPerWeek(String currencySell, String currencyBuy){
        List<FxRatesResponse> responses = new ArrayList<>();
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate today = LocalDate.now();

        for(int i = 0; i < 7; i++){
            LocalDate currentDate = today.minusDays(i);
            String formattedDate = currentDate.format(pattern);

            FxRatesResponse result = fxRatesApiClient.getConvertationRate(formattedDate, currencyBuy, currencySell);
            responses.add(result);
     
        }
        
    }

}