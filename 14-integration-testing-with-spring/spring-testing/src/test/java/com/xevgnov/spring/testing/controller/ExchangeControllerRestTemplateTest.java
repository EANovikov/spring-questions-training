package com.xevgnov.spring.testing.controller;

import static com.xevgnov.spring.testing.util.TestDataUtility.DATE_PATTERN;
import static com.xevgnov.spring.testing.util.TestDataUtility.getPriceHistory;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.xevgnov.spring.testing.dto.ExchangeStatistics;
import com.xevgnov.spring.testing.service.StatisticsService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ExchangeControllerRestTemplateTest {

    @MockBean
    private StatisticsService statisticsService;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void getExchangeStatistisForValidCurrencies() {
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

        // When
        ResponseEntity<ExchangeStatistics> response = testRestTemplate.getForEntity("/{sellCurrency}/to/{buyCurrency}",
                ExchangeStatistics.class, sellCurrency, buyCurrency);
        // Then
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).isEqualTo(exchangeStatistics);
    }

}
