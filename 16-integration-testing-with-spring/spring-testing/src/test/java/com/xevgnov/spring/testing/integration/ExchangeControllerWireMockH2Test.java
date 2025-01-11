package com.xevgnov.spring.testing.integration;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.xevgnov.spring.testing.util.TestDataUtility.DATE_PATTERN;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.wiremock.spring.EnableWireMock;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.xevgnov.spring.testing.client.FxRatesApiClient;
import com.xevgnov.spring.testing.dto.ExchangeStatistics;
import com.xevgnov.spring.testing.dto.FxRatesResponse;

// import feign.Feign;
// import feign.Logger;
// import feign.gson.GsonDecoder;
// import feign.gson.GsonEncoder;
// import feign.okhttp.OkHttpClient;
// import feign.slf4j.Slf4jLogger;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@EnableWireMock
public class ExchangeControllerWireMockH2Test {
    @Value("${wiremock.server.baseUrl}")
    private String wireMockUrl;

    @Autowired
    private TestRestTemplate testRestTemplate;

    // @Autowired
    // private FxRatesApiClient fxRatesApiClient;
    // private FxRatesApiClient fxRatesApiClient = Feign.builder()
    //         .client(new OkHttpClient())
    //         .encoder(new GsonEncoder())
    //         .decoder(new GsonDecoder())
    //         .logger(new Slf4jLogger(FxRatesApiClient.class))
    //         .logLevel(Logger.Level.FULL)
    //         .target(FxRatesApiClient.class, wireMockUrl);

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetStatisticsReturns200Status() throws JsonProcessingException {
        // Given
        String sellCurrency = "EUR";
        String buyCurrency = "USD";
        ZonedDateTime zonedDatetime = ZonedDateTime.now();
        // ZonedDateTime zonedDatetime = ZonedDateTime.of(
        //     LocalDate.of(2025, 1, 12), LocalTime.of(12, 0, 0), ZoneId.of("UTC"));
        String date = zonedDatetime.format(DATE_PATTERN);
        FxRatesResponse fxRatesResponse = FxRatesResponse.builder()
                .base(sellCurrency)
                .privacy("privacy")
                .terms("terms")
                .success(true)
                .timestamp(zonedDatetime.toInstant())
                .date(zonedDatetime)
                .rates(Map.of(buyCurrency, 1.0271234))
                .build();
        var mockedResponse = WireMock.aResponse()
                .withBody(objectMapper.writeValueAsString(fxRatesResponse))
                .withStatus(HttpStatus.OK.value())
                .withHeader("Content-Type", "application/json");
        stubFor(
                get(String.format("/historical?date=%s&currencies=%s&base=%s", date, buyCurrency, sellCurrency))
                        .willReturn(mockedResponse));

        // When
        ResponseEntity<ExchangeStatistics> response = testRestTemplate.getForEntity("/{sellCurrency}/to/{buyCurrency}",
                ExchangeStatistics.class, sellCurrency, buyCurrency);
        // Then
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
    }

}
