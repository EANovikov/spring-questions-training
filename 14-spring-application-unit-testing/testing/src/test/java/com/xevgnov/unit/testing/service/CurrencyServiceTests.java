package com.xevgnov.unit.testing.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.xevgnov.unit.testing.client.FxRatesApiClient;
import com.xevgnov.unit.testing.repository.ExchangeRecordsRepository;

@ExtendWith(MockitoExtension.class)
public class CurrencyServiceTests {

    @Mock
    private FxRatesApiClient fxRatesApiClient;
    @Mock
    private ExchangeRecordsRepository exchangeRecordsRepository;
    @Mock
    private ObjectMapper objectMapper = new ObjectMapper();

    @InjectMocks
    private CurrencyServiceImpl currencyService;

    @BeforeEach
    public void setUp() {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Test
    public void shouldGetFxRateFromRepositoryForExistingDate() {
        //Given

        //When
         currencyService.getFxRateForDate("", "", "");
        //Then
    }
    @Test
    public void shouldGetFxRateFromApiIfDateDoeNotExistInRepositoryAndSaveItToRepository() {
        //Given

        //When
        currencyService.getFxRateForDate("", "", "");
        //Then
    }

    @Test
    public void shouldGetFxRateFromApiSuccessfullyIfFailedToSaveRecordToRepository() {
        //Given

        //When
        currencyService.getFxRateForDate("", "", "");
        //Then
    }

    @Test
    public void shouldGetFxRateFromApiSuccessfullyIfFailedGetRecordFromRepository() {
        //Given

        //When
        currencyService.getFxRateForDate("", "", "");
        //Then
    }

    @Test
    public void shouldThowCurrencyServiceExceptionIfFailedToGetDataFromRepositoryAndApi() {
        //Given

        //When
        currencyService.getFxRateForDate("", "", "");
        //Then
    }


}
