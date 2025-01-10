package com.xevgnov.spring.testing.integration;

@SpringBootTest
@EnableWireMock
public class ExchangeControllerWireMockH2Test {
    @Value("${wiremock.server.baseUrl}")
    private String wireMockUrl;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    FxRatesApiClient fxRatesApiClient;

    @Test
    void testGetStatisticsReturns200Status() {
        // Given
        fxRatesApiClient.setBaseUrl(wireMockUrl);
        
        String sellCurrency = "EUR";
        String buyCurrency = "USD";
        String date = LocalDate.now().format(DATE_PATTERN);
        FxRatesResponse fxRatesResponse = FxRatesResponse.builder()
                    .base(sellCurrency)
                    .privacy("privacy")
                    .terms("terms")
                    .success(true)
                    .timestamp(Instant.now())
                    .date(ZonedDateTime.now())
                    .rates(Map.of(buyCurrency, rate))
                    .build();
        stubFor(
            get("/historical?date={date}2&currencies={buyCurrency}&base={sellCurrency}", date, buyCurrency, sellCurrency)
            .willReturn(fxRatesResponse));


        // When
        ResponseEntity<ExchangeStatistics> response = testRestTemplate.getForEntity("/{sellCurrency}/to/{buyCurrency}",
                ExchangeStatistics.class, sellCurrency, buyCurrency);
        // Then
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
    }


}
