package com.xevgnov.spring.testing.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.time.Instant;
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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.xevgnov.spring.testing.client.FxRatesApiClient;
import com.xevgnov.spring.testing.dto.FxRatesResponse;
import com.xevgnov.spring.testing.entity.EcxcangeRecord;
import com.xevgnov.spring.testing.entity.ExchangeRecordId;
import com.xevgnov.spring.testing.exception.CurrencyServiceException;
import com.xevgnov.spring.testing.repository.ExchangeRecordsRepository;

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
    public void shouldGetFxRateFromApiIfDateDoesNotExistInRepositoryAndSaveItToRepository()
            throws JsonProcessingException {
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
        verify(fxRatesApiClient).getConvertationRate(date, "USD", "EUR");
        verifyNoMoreInteractions(fxRatesApiClient);
    }

    @Test
    public void shouldGetFxRateFromApiSuccessfullyIfFailedToSaveRecordToRepository() throws JsonProcessingException {
        // Given
        when(exchangeRecordsRepository.findById(exchangeRecordId))
                .thenReturn(Optional.empty());
        when(fxRatesApiClient.getConvertationRate(date, "USD", "EUR")).thenReturn(fxRatesResponse);
        when(exchangeRecordsRepository.save(ecxcangeRecord))
                .thenThrow(new RuntimeException("failed to save to DB"));
        // When
        FxRatesResponse actualFxRatesResponse = currencyService.getFxRateForDate(date, "USD", "EUR");
        // Then
        assertThat(actualFxRatesResponse).isEqualTo(fxRatesResponse);

        verify(exchangeRecordsRepository).findById(exchangeRecordId);
        verify(exchangeRecordsRepository).save(ecxcangeRecord);
        verify(objectMapper, times(2)).writeValueAsString(fxRatesResponse);
        verifyNoMoreInteractions(exchangeRecordsRepository);
        verify(fxRatesApiClient).getConvertationRate(date, "USD", "EUR");

        verifyNoMoreInteractions(fxRatesApiClient);
    }

    @Test
    public void shouldGetFxRateFromApiSuccessfullyIfFailedGetRecordFromRepository() throws JsonProcessingException {
        // Given
        when(exchangeRecordsRepository.findById(exchangeRecordId))
                .thenThrow(new RuntimeException("failed to get from DB"));
        when(fxRatesApiClient.getConvertationRate(date, "USD", "EUR")).thenReturn(fxRatesResponse);
        // When
        FxRatesResponse actualFxRatesResponse = currencyService.getFxRateForDate(date, "USD", "EUR");
        // Then
        assertThat(actualFxRatesResponse).isEqualTo(fxRatesResponse);

        verify(exchangeRecordsRepository).findById(exchangeRecordId);
        verify(exchangeRecordsRepository).save(ecxcangeRecord);
        verify(objectMapper, times(2)).writeValueAsString(fxRatesResponse);
        verifyNoMoreInteractions(exchangeRecordsRepository);
        verify(fxRatesApiClient).getConvertationRate(date, "USD", "EUR");
        ;
        verifyNoMoreInteractions(fxRatesApiClient);
    }

    @Test
    public void shouldGetFxRateFromApiSuccessfullyIfDeserializeSerializeFxRateResponse()
            throws JsonProcessingException {
        // Given
        when(exchangeRecordsRepository.findById(exchangeRecordId))
                .thenReturn(Optional.of(ecxcangeRecord));
        when(objectMapper.writeValueAsString(fxRatesResponse))
                .thenThrow(new RuntimeException("JSON issue"));
        when(objectMapper.readValue(fxRatesInDb, FxRatesResponse.class))
                .thenThrow(new RuntimeException("JSON issue"));
        when(fxRatesApiClient.getConvertationRate(date, "USD", "EUR")).thenReturn(fxRatesResponse);

        // When
        FxRatesResponse actualFxRatesResponse = currencyService.getFxRateForDate(date, "USD", "EUR");
        // Then
        assertThat(actualFxRatesResponse).isEqualTo(fxRatesResponse);

        verify(exchangeRecordsRepository).findById(exchangeRecordId);
        verify(objectMapper, times(3)).writeValueAsString(fxRatesResponse);
        verifyNoMoreInteractions(exchangeRecordsRepository);
        verify(fxRatesApiClient).getConvertationRate(date, "USD", "EUR");
        verifyNoMoreInteractions(fxRatesApiClient);
    }

    @Test
    public void shouldThowCurrencyServiceExceptionIfFailedToGetDataFromRepositoryAndApi()
            throws JsonProcessingException {
        // Given
        when(exchangeRecordsRepository.findById(exchangeRecordId))
                .thenReturn(Optional.empty());
        Exception expectedException = new RuntimeException("API issue");
        when(fxRatesApiClient.getConvertationRate(date, "USD", "EUR"))
                .thenThrow(expectedException);

        // When
        assertThatThrownBy(() -> currencyService.getFxRateForDate(date, "USD", "EUR"))
                .isInstanceOf(CurrencyServiceException.class)
                .hasMessage(String.format("Failed to get %s / %s currency pair data for the date %s due to [%s]", "EUR",
                        "USD", date, expectedException.getMessage()));
        verify(exchangeRecordsRepository).findById(exchangeRecordId);
        verify(objectMapper, times(1)).writeValueAsString(fxRatesResponse);
        verifyNoMoreInteractions(exchangeRecordsRepository);
        verify(fxRatesApiClient).getConvertationRate(date, "USD", "EUR");
        ;
        verifyNoMoreInteractions(fxRatesApiClient);
    }

}
