package com.xevgnov.spring.testing.integration;

import com.xevgnov.spring.testing.dto.ExceptionResponse;
import com.xevgnov.spring.testing.dto.ExchangeStatistics;
import com.xevgnov.spring.testing.exception.StatisticsServiceException;
import com.xevgnov.spring.testing.service.StatisticsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDate;
import java.util.Map;

import static com.xevgnov.spring.testing.util.TestDataUtility.DATE_PATTERN;
import static com.xevgnov.spring.testing.util.TestDataUtility.getPriceHistory;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@AutoConfigureTestRestTemplate
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ExchangeControllerRestTemplateTest {

    @MockitoBean
    private StatisticsService statisticsService;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void testGetStatisticsReturns200Status() {
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
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).isEqualTo(exchangeStatistics);
    }

    @ParameterizedTest
    @ValueSource(strings = {"LONG", "SH", " ", "123", "@#$"})
    void testGetStatisticsReturns400ExceptionResponseIfSellCurrencyIsNot3UpperCasedCharacters(String sellCurrency)
            throws Exception {
        // Given
        String expectedMessage = "Currency code must contain 3 upper-cased caracters";
        String expectedException = "jakarta.validation.ConstraintViolationException";
        // When Then
        ResponseEntity<ExceptionResponse> response = testRestTemplate.getForEntity("/{sellCurrency}/to/{buyCurrency}",
                ExceptionResponse.class, sellCurrency, "USD");
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode().is4xxClientError()).isTrue();
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getMessage()).contains(expectedMessage);
        assertThat(response.getBody().getDetails()).contains(expectedException);
    }

    @ParameterizedTest
    @ValueSource(strings = {"LONG", "SH", " ", "123", "@#$"})
    void testGetStatisticsReturns400ExceptionResponseIfBuyCurrencyIsNot3UpperCasedCharacters(String buyCurrency)
            throws Exception {

        // Given
        String expectedMessage = "Currency code must contain 3 upper-cased caracters";
        String expectedException = "jakarta.validation.ConstraintViolationException";
        // When Then
        ResponseEntity<ExceptionResponse> response = testRestTemplate.getForEntity("/{sellCurrency}/to/{buyCurrency}",
                ExceptionResponse.class, "USD", buyCurrency);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode().is4xxClientError()).isTrue();
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getMessage()).contains(expectedMessage);
        assertThat(response.getBody().getDetails()).contains(expectedException);
    }

    @Test
    void testGetStatisticsReturns500ExceptionResponseIfStatisticsServiceThrownException() throws Exception {
        // Given
        String sellCurrency = "EUR";
        String buyCurrency = "USD";
        Exception exception = new StatisticsServiceException("test issue", new RuntimeException());
        when(statisticsService.getStatistics(sellCurrency, buyCurrency)).thenThrow(exception);
        // When Then
        ResponseEntity<ExceptionResponse> response = testRestTemplate.getForEntity("/{sellCurrency}/to/{buyCurrency}",
                ExceptionResponse.class, sellCurrency, buyCurrency);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode().is5xxServerError()).isTrue();
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getMessage()).contains(exception.getMessage());
        assertThat(response.getBody().getDetails()).contains(exception.toString());
    }
}
