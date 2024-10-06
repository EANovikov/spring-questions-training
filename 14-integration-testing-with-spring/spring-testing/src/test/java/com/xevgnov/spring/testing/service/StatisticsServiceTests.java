package com.xevgnov.spring.testing.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.xevgnov.spring.testing.dto.ExchangeStatistics;
import com.xevgnov.spring.testing.dto.FxRatesResponse;
import com.xevgnov.spring.testing.exception.StatisticsServiceException;

@ExtendWith(MockitoExtension.class)
public class StatisticsServiceTests {

    @Mock
    private CurrencyService currencyService;

    @InjectMocks
    private StatisticsServiceImpl statisticsService;

    private final DateTimeFormatter datePattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Test
    public void testStatisticServiceRetrievesDataForLastWeek() {
        // Given
        List<FxRatesResponse> responses = prepareRequestsForLastWeek("EUR", "USD");

        Map<String, Double> expectedPriceHistory = preparePriceHistory();
        List<String> expectedDates = expectedPriceHistory.keySet().stream().toList();

        doReturn(responses.get(0))
                .when(currencyService).getFxRateForDate(expectedDates.get(0), "USD", "EUR");
        doReturn(responses.get(1))
                .when(currencyService).getFxRateForDate(expectedDates.get(1), "USD", "EUR");
        doReturn(responses.get(2))
                .when(currencyService).getFxRateForDate(expectedDates.get(2), "USD", "EUR");
        doReturn(responses.get(3))
                .when(currencyService).getFxRateForDate(expectedDates.get(3), "USD", "EUR");
        doReturn(responses.get(4))
                .when(currencyService).getFxRateForDate(expectedDates.get(4), "USD", "EUR");
        doReturn(responses.get(5))
                .when(currencyService).getFxRateForDate(expectedDates.get(5), "USD", "EUR");
        doReturn(responses.get(6))
                .when(currencyService).getFxRateForDate(expectedDates.get(6), "USD", "EUR");

        double expectedMiddlePrice = expectedPriceHistory.values()
                .stream().mapToDouble(value -> value.doubleValue()).average().getAsDouble();
        // When
        ExchangeStatistics statisticsResult = statisticsService.getStatistics("EUR", "USD");
        // Then
        assertThat(statisticsResult).isNotNull();
        assertThat(statisticsResult.getSellCurrency()).isEqualTo("EUR");
        assertThat(statisticsResult.getBuyCurrency()).isEqualTo("USD");
        assertThat(statisticsResult.getDate()).isEqualTo(LocalDateTime.now().format(datePattern));
        assertThat(statisticsResult.getAveragePrice()).isEqualTo(expectedMiddlePrice);
        assertThat(statisticsResult.getCurrentPrice()).isEqualTo(1.1);
        assertThat(statisticsResult.getPriceHistory()).isNotEmpty();
        assertThat(statisticsResult.getPriceHistory()).isEqualTo(expectedPriceHistory);
    }

    @Test
    public void testStatisticServiceRetrievesDataThrowsStatisticsServiceExceptionOnMissingCurrencyData() {
               // Given
               doReturn(null)
                       .when(currencyService).getFxRateForDate(anyString(), anyString(), anyString());
              
               // When Then
               assertThatThrownBy(()->
               statisticsService.getStatistics("EUR", "USD"))
               .isInstanceOf(StatisticsServiceException.class)
               .hasMessageContaining("Failed to collect statistics for the pair: EUR/USD due to: ");
    }

    private Map<String, Double> preparePriceHistory() {
        Map<String, Double> priceHistory = new LinkedHashMap<>();
        double rate = 1.10000D;
        for (int i = 0; i < 7; i++) {
            priceHistory.put(LocalDateTime.now().minusDays(i).format(datePattern), rate);
            rate += 0.01D;
        }
        return priceHistory;
    }

    private List<FxRatesResponse> prepareRequestsForLastWeek(String currSell, String currBuy) {
        List<FxRatesResponse> results = new ArrayList<>();
        double rate = 1.10000D;
        for (int i = 0; i < 7; i++) {
            FxRatesResponse fxRatesResponse = FxRatesResponse.builder()
                    .base("EUR")
                    .privacy("privacy")
                    .terms("terms")
                    .success(true)
                    .timestamp(Instant.now())
                    .date(ZonedDateTime.now().minusDays(i))
                    .rates(Map.of("USD", rate))
                    .build();
            results.add(fxRatesResponse);
            rate += 0.01D;
        }
        return results;
    }

}
