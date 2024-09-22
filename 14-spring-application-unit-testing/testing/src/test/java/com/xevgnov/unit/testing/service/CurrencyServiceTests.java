package com.xevgnov.unit.testing.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.xevgnov.unit.testing.client.FxRatesApiClient;
import com.xevgnov.unit.testing.dto.FxRatesResponse;
import com.xevgnov.unit.testing.entity.EcxcangeRecord;
import com.xevgnov.unit.testing.entity.ExchangeRecordId;
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
    public void shouldGetFxRateFromRepositoryForExistingDate() throws JsonProcessingException {
        //Given
        ExchangeRecordId exchangeRecordId = new ExchangeRecordId("2024-09-22", "EUR", "USD");
        FxRatesResponse fxRatesResponse = FxRatesResponse.builder()
                    .base("EUR")
                    .privacy("privacy")
                    .terms("terms")
                    .success(true)
                    .timestamp(Instant.now())
                    .date(ZonedDateTime.now())
                    .rates(Map.of("USD", 1.1D))
                    .build();
       /*           Optional<EcxcangeRecord> responseFromDb = exchangeRecordsRepository.findById(recordId);
            if (responseFromDb.isPresent()) {
                return Optional.of(objectMapper.readValue(
                        responseFromDb.get().getFxRatesResponse(),
                        FxRatesResponse.class));
            */  
            EcxcangeRecord ecxcangeRecord = EcxcangeRecord.builder()
               .id(exchangeRecordId)
               .fxRatesResponse(objectMapper.writeValueAsString(fxRatesResponse))
               .build();
        when(exchangeRecordsRepository.findById(exchangeRecordId)).thenReturn(null)
             .thenReturn(Optional.of(ecxcangeRecord));  
        //When
        FxRatesResponse actualFxRatesResponse = currencyService.getFxRateForDate("2024-09-22", "USD", "EUR");
        //Then
        assertThat(actualFxRatesResponse).isNotNull();
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
