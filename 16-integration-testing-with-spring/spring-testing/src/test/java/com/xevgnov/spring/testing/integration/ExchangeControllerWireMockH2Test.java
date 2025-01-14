package com.xevgnov.spring.testing.integration;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.xevgnov.spring.testing.util.TestDataUtility.DATE_PATTERN;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.wiremock.spring.ConfigureWireMock;
import org.wiremock.spring.EnableWireMock;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.xevgnov.spring.testing.dto.ExchangeStatistics;
import com.xevgnov.spring.testing.dto.FxRatesResponse;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@EnableWireMock(@ConfigureWireMock(port = 8888))
public class ExchangeControllerWireMockH2Test {

        @Autowired
        private TestRestTemplate testRestTemplate;

        @Autowired
        private ObjectMapper objectMapper;

        @Test
        @Sql(scripts = "file:src/test/resources/create-test-data.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
        @Sql(scripts = "file:src/test/resources/cleanup-test-data.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
        void testGetStatisticsRetrievesCurrencyDataFromDatabaseAndReturns200Status() throws JsonProcessingException {
                // Given
                String sellCurrency = "EUR";
                String buyCurrency = "USD";

                // When
                ResponseEntity<ExchangeStatistics> response = testRestTemplate.getForEntity(
                                "/{sellCurrency}/to/{buyCurrency}",
                                ExchangeStatistics.class, sellCurrency, buyCurrency);
                // Then
                assertThat(response).isNotNull();
                assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
                assertThat(response.getBody()).isNotNull();
                assertThat(response.getBody().getDate()).isEqualTo(LocalDate.now().format(DATE_PATTERN));
                assertThat(response.getBody().getSellCurrency()).isEqualTo(sellCurrency);
                assertThat(response.getBody().getBuyCurrency()).isEqualTo(buyCurrency);
                assertThat(response.getBody().getCurrentPrice()).isEqualTo(1.025083665);
                assertThat(response.getBody().getAveragePrice()).isEqualTo(1.031602123);
                Map<String, Double> expectedPriceHistory = new LinkedHashMap();
                expectedPriceHistory.put(ZonedDateTime.now().format(DATE_PATTERN), 1.025083665);
                expectedPriceHistory.put(ZonedDateTime.now().minusDays(1).format(DATE_PATTERN), 1.030003905);
                expectedPriceHistory.put(ZonedDateTime.now().minusDays(2).format(DATE_PATTERN), 1.031672155);
                expectedPriceHistory.put(ZonedDateTime.now().minusDays(3).format(DATE_PATTERN), 1.034350654);
                expectedPriceHistory.put(ZonedDateTime.now().minusDays(4).format(DATE_PATTERN), 1.038248979);
                expectedPriceHistory.put(ZonedDateTime.now().minusDays(5).format(DATE_PATTERN), 1.030651418);
                expectedPriceHistory.put(ZonedDateTime.now().minusDays(6).format(DATE_PATTERN), 1.031204085);
                assertThat(response.getBody().getPriceHistory()).isEqualTo(expectedPriceHistory);
        }

        @Test
        @Sql(scripts = "file:src/test/resources/cleanup-test-data.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
        void testGetStatisticsRetrievesDataFromFxRatesApiClientAndNoDataFromDatabaseAndReturns200Status()
                        throws JsonProcessingException {
                // Given
                String sellCurrency = "EUR";
                String buyCurrency = "USD";
                ZonedDateTime curreDateTime = ZonedDateTime.now();
                stubFxRatesCallsForLastWeek(curreDateTime, sellCurrency, buyCurrency);

                // When
                ResponseEntity<ExchangeStatistics> response = testRestTemplate.getForEntity(
                                "/{sellCurrency}/to/{buyCurrency}",
                                ExchangeStatistics.class, sellCurrency, buyCurrency);
                // Then
                assertThat(response).isNotNull();
                assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
                assertThat(response.getBody()).isNotNull();
                assertThat(response.getBody().getDate()).isEqualTo(LocalDate.now().format(DATE_PATTERN));
                assertThat(response.getBody().getSellCurrency()).isEqualTo(sellCurrency);
                assertThat(response.getBody().getBuyCurrency()).isEqualTo(buyCurrency);
                assertThat(response.getBody().getCurrentPrice()).isEqualTo(1.0271);
                assertThat(response.getBody().getAveragePrice()).isCloseTo(1.0273999, Percentage.withPercentage(99.9));
                Map<String, Double> expectedPriceHistory = new LinkedHashMap();
                expectedPriceHistory.put(ZonedDateTime.now().format(DATE_PATTERN), 1.0271);
                expectedPriceHistory.put(ZonedDateTime.now().minusDays(1).format(DATE_PATTERN), 1.0272);
                expectedPriceHistory.put(ZonedDateTime.now().minusDays(2).format(DATE_PATTERN), 1.0273);
                expectedPriceHistory.put(ZonedDateTime.now().minusDays(3).format(DATE_PATTERN), 1.0274);
                expectedPriceHistory.put(ZonedDateTime.now().minusDays(4).format(DATE_PATTERN), 1.0275);
                expectedPriceHistory.put(ZonedDateTime.now().minusDays(5).format(DATE_PATTERN), 1.0276);
                expectedPriceHistory.put(ZonedDateTime.now().minusDays(6).format(DATE_PATTERN), 1.0277);
                assertThat(response.getBody().getPriceHistory()).isEqualTo(expectedPriceHistory);
        }

        private void stubFxRatesCallsForLastWeek(ZonedDateTime zonedDatetime, String sellCurrency, String buyCurrency)
                        throws JsonProcessingException {
                double rate = 1.0271;
                for (int i = 0; i < 7; i++) {
                        String date = zonedDatetime.minusDays(i).format(DATE_PATTERN);
                        FxRatesResponse fxRatesResponse = FxRatesResponse.builder()
                                        .base(sellCurrency)
                                        .privacy("privacy")
                                        .terms("terms")
                                        .success(true)
                                        .timestamp(zonedDatetime.toInstant())
                                        .date(zonedDatetime)
                                        .rates(Map.of(buyCurrency, rate))
                                        .build();
                        var mockedResponse = WireMock.aResponse()
                                        .withBody(objectMapper.writeValueAsString(fxRatesResponse))
                                        .withStatus(HttpStatus.OK.value())
                                        .withHeader("Content-Type", "application/json");
                        stubFor(
                                        get(
                                                        String.format("/historical?date=%s&currencies=%s&base=%s",
                                                                        date, buyCurrency, sellCurrency))
                                                        .willReturn(mockedResponse));
                        rate = Double.sum(rate, 0.0001);
                }
        }

}
