package com.xevgnov.unit.testing.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;
import java.util.TimeZone;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
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
    @Spy
    private ObjectMapper objectMapper = new ObjectMapper();

    @InjectMocks
    private CurrencyServiceImpl currencyService;

    private final DateTimeFormatter datePattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final String date = ZonedDateTime.now().format(datePattern);
    private final ExchangeRecordId exchangeRecordId = new ExchangeRecordId(date, "EUR", "USD");
    private final FxRatesResponse fxRatesResponse = FxRatesResponse.builder()
            .base("EUR")
            .privacy("privacy")
            .terms("terms")
            .success(true)
            .timestamp(Instant.now())
            .date(ZonedDateTime.now())
            .rates(Map.of("USD", 1.1D))
            .build();
    private String fxRatesInDb;
    private EcxcangeRecord ecxcangeRecord;

    @BeforeEach
    public void setUp() throws JsonProcessingException {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.enable(SerializationFeature.WRITE_DATES_WITH_CONTEXT_TIME_ZONE);
        objectMapper.setTimeZone(TimeZone.getDefault());

        fxRatesInDb = objectMapper.writeValueAsString(fxRatesResponse);   
        ecxcangeRecord = EcxcangeRecord.builder()
                .id(exchangeRecordId)
                .fxRatesResponse(fxRatesInDb)
                .build();
    }

    @Test
    public void shouldGetFxRateFromRepositoryForExistingDate() throws JsonProcessingException {
        // Given
        when(exchangeRecordsRepository.findById(exchangeRecordId))
                .thenReturn(Optional.of(ecxcangeRecord));
        // When
        FxRatesResponse actualFxRatesResponse = currencyService.getFxRateForDate(date, "USD", "EUR");
        // Then
        assertThat(actualFxRatesResponse).isEqualTo(fxRatesResponse);
        
        verify(exchangeRecordsRepository).findById(exchangeRecordId);
        verify(objectMapper).readValue(fxRatesInDb, FxRatesResponse.class);
        verifyNoMoreInteractions(exchangeRecordsRepository);
        verifyNoInteractions(fxRatesApiClient);
    }

    @Test
    public void shouldGetFxRateFromApiIfDateDoeNotExistInRepositoryAndSaveItToRepository() throws JsonProcessingException {
          // Given
          when(exchangeRecordsRepository.findById(exchangeRecordId))
                  .thenReturn(Optional.empty());
          when(fxRatesApiClient.getConvertationRate(date, "USD", "EUR")).thenReturn(fxRatesResponse);
          // When
          FxRatesResponse actualFxRatesResponse = currencyService.getFxRateForDate(date, "USD", "EUR");
          // Then
          assertThat(actualFxRatesResponse).isEqualTo(fxRatesResponse);
         
          verify(exchangeRecordsRepository).findById(exchangeRecordId);
          verify(exchangeRecordsRepository).save(ecxcangeRecord);
          verify(objectMapper, times(2)).writeValueAsString(fxRatesResponse);
          verifyNoMoreInteractions(exchangeRecordsRepository);
          verify(fxRatesApiClient).getConvertationRate(date, "USD", "EUR");;
          verifyNoMoreInteractions(fxRatesApiClient);
        }

    @Test
    public void shouldGetFxRateFromApiSuccessfullyIfFailedToSaveRecordToRepository() {
        // Given

        // When
        currencyService.getFxRateForDate("", "", "");
        // Then
    }

    @Test
    public void shouldGetFxRateFromApiSuccessfullyIfFailedGetRecordFromRepository() {
        // Given

        // When
        currencyService.getFxRateForDate("", "", "");
        // Then
    }

    @Test
    public void shouldThowCurrencyServiceExceptionIfFailedToGetDataFromRepositoryAndApi() {
        // Given

        // When
        currencyService.getFxRateForDate("", "", "");
        // Then
    }


}
