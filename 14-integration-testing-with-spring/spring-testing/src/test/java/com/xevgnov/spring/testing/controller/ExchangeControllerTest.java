package com.xevgnov.spring.testing.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.xevgnov.spring.testing.dto.ExchangeStatistics;
import com.xevgnov.spring.testing.service.StatisticsService;

@WebMvcTest(ExchangeController.class)
public class ExchangeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StatisticsService statisticsService;

    private final DateTimeFormatter datePattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Test
    void testGetStatisticsReturn200Status() throws Exception {
        //Given
        String sellCurrency = "USD";
        String buyCurrency = "EUR";
        String date = LocalDate.now().format(datePattern);
        Double averagePrice = 0.91;
        Double currentPrice = 0.91;
        Map<String, Double> priceHistory = getPriceHistory();
        ExchangeStatistics exchangeStatistics = ExchangeStatistics.builder()
        .sellCurrency(sellCurrency)
        .buyCurrency(buyCurrency)
        .date(date)
        .averagePrice(averagePrice)
        .currentPrice(currentPrice)
        .priceHistory(priceHistory)
        .build();

        when(statisticsService.getStatistics(sellCurrency, buyCurrency)).thenReturn(exchangeStatistics);
        //When Then
        mockMvc.perform(get("/{sellCurrency}/to/{buyCurrency}", sellCurrency, buyCurrency))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.sellCurrency").value(sellCurrency))
        .andExpect(jsonPath("$.buyCurrency").value(buyCurrency))
        .andExpect(jsonPath("$.date").value(date))
        .andExpect(jsonPath("$.averagePrice").value(averagePrice))
        .andExpect(jsonPath("$.currentPrice").value(currentPrice))
        .andExpect(jsonPath("$.priceHistory").value(priceHistory));
    }


    @ParameterizedTest
    @ValueSource(strings = {"LONG", "SH", "", "123", "@#$"})
    void testGetStatisticsReturn400ExceptionResponseIfSellCurrencyIsNot3UpperCasedCharacters(String sellCurrency) throws Exception {
        //When Then
        mockMvc.perform(get("/{sellCurrency}/to/{buyCurrency}", sellCurrency, "USD"))
        .andExpect(status().is4xxClientError())
        //TODO validae exception respone: ExceptionResponse
        .andExpect(jsonPath("$.sellCurrency").value(sellCurrency));
    }

    @Test
    void testGetStatisticsReturn400ExceptionResponseIfBuyCurrencyIsNot3UpperCasedCharacters() throws Exception {
        
    }

    private Map<String, Double> getPriceHistory() {
        Map<String, Double> priceHistory = new LinkedHashMap<>();
        for (int i = 0; i < 7; i++) {
            double value = i % 2 == 0 ? 0.91 : 0.90;
            priceHistory.put(LocalDate.now().minusDays(i).format(datePattern), value);
        }
        return priceHistory;
    }
}
