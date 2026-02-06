package com.xevgnov.spring.testing.integration.slice.controller;

import com.xevgnov.spring.testing.controller.ExchangeController;
import com.xevgnov.spring.testing.dto.ExchangeStatistics;
import com.xevgnov.spring.testing.exception.StatisticsServiceException;
import com.xevgnov.spring.testing.service.StatisticsService;
import org.hamcrest.core.StringContains;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Map;

import static com.xevgnov.spring.testing.util.TestDataUtility.DATE_PATTERN;
import static com.xevgnov.spring.testing.util.TestDataUtility.getPriceHistory;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ExchangeController.class)
public class ExchangeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StatisticsService statisticsService;

    @Test
    void testGetStatisticsReturns200Status() throws Exception {
        // Given
        String sellCurrency = "USD";
        String buyCurrency = "EUR";
        String date = LocalDate.now().format(DATE_PATTERN);
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
        // When Then
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
    @ValueSource(strings = {"LONG", "SH", " ", "123", "@#$"})
    void testGetStatisticsReturns400ExceptionResponseIfSellCurrencyIsNot3UpperCasedCharacters(String sellCurrency)
            throws Exception {
        // Given
        String expectedMessage = "Currency code must contain 3 upper-cased caracters";
        String expectedException = "jakarta.validation.ConstraintViolationException";
        // When Then
        mockMvc.perform(get("/{sellCurrency}/to/{buyCurrency}", sellCurrency, "USD"))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.message")
                        .value(StringContains.containsString(expectedMessage)))
                .andExpect(jsonPath("$.details")
                        .value(StringContains.containsString(expectedException)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"LONG", "SH", " ", "123", "@#$"})
    void testGetStatisticsReturns400ExceptionResponseIfBuyCurrencyIsNot3UpperCasedCharacters(String buyCurrency)
            throws Exception {

        // Given
        String expectedMessage = "Currency code must contain 3 upper-cased caracters";
        String expectedException = "jakarta.validation.ConstraintViolationException";
        // When Then
        mockMvc.perform(get("/{sellCurrency}/to/{buyCurrency}", "USD", buyCurrency))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.message")
                        .value(StringContains.containsString(expectedMessage)))
                .andExpect(jsonPath("$.details")
                        .value(StringContains.containsString(expectedException)));
    }

    @Test
    void testGetStatisticsReturns500ExceptionResponseIfStatisticsServiceThrownException() throws Exception {
        // Given
        String sellCurrency = "EUR";
        String buyCurrency = "USD";
        Exception exception = new StatisticsServiceException("test issue", new RuntimeException());
        when(statisticsService.getStatistics(sellCurrency, buyCurrency)).thenThrow(exception);
        // When Then
        mockMvc.perform(get("/{sellCurrency}/to/{buyCurrency}", sellCurrency, buyCurrency))
                .andExpect(status().is5xxServerError())
                .andExpect(jsonPath("$.message")
                        .value(StringContains.containsString(exception.getMessage())))
                .andExpect(jsonPath("$.details")
                        .value(StringContains.containsString(exception.toString())));
    }

}
