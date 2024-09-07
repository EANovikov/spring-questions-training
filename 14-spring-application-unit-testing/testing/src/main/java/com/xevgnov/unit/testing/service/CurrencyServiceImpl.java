package com.xevgnov.unit.testing.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xevgnov.unit.testing.client.FxRatesApiClient;
import com.xevgnov.unit.testing.dto.FxRatesResponse;
import com.xevgnov.unit.testing.entity.EcxcangeRecord;
import com.xevgnov.unit.testing.entity.ExchangeRecordId;
import com.xevgnov.unit.testing.repository.ExchangeRecordsRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CurrencyServiceImpl implements CurrencyService {

    private final FxRatesApiClient fxRatesApiClient;
    private final ExchangeRecordsRepository exchangeRecordsRepository;
    private final ObjectMapper objectMapper;

    public CurrencyServiceImpl(FxRatesApiClient fxRatesApiClient,
            ExchangeRecordsRepository exchangeRecordsRepository,
            ObjectMapper objectMapper) {
        this.fxRatesApiClient = fxRatesApiClient;
        this.exchangeRecordsRepository = exchangeRecordsRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public FxRatesResponse getFxRateForDate(String date, String currencyBuy, String currencySell) {
        ExchangeRecordId recordId = new ExchangeRecordId(date, currencySell, currencyBuy);
        Optional<FxRatesResponse> databaseResponse = getFxRatesFromDatabase(recordId);
        if (databaseResponse.isPresent()) {
            return databaseResponse.get();
        }
        return getFxRatesFromApi(recordId);
    }

    private Optional<FxRatesResponse> getFxRatesFromDatabase(ExchangeRecordId recordId) {
        log.info("About to get {} from database", recordId);
        try {
            Optional<EcxcangeRecord> responseFromDb = exchangeRecordsRepository.findById(recordId);
            if (responseFromDb.isPresent()) {
                return Optional.of(objectMapper.readValue(
                        responseFromDb.get().getFxRatesResponse(),
                        FxRatesResponse.class));
            }
        } catch (Throwable e) {
            log.error("Failed to get FX rates for {} from the database due to {}",
                    recordId, e.getMessage(), e);
        }
        return Optional.empty();
    }

    private FxRatesResponse getFxRatesFromApi(ExchangeRecordId recordId) {
        log.info("About to get {} from API", recordId);
        FxRatesResponse apiResponse = fxRatesApiClient.getConvertationRate(recordId.getDate(),
                recordId.getBuyCurrency(), recordId.getSellCurrency());
        saveFxRateResponseToDb(recordId, apiResponse);
        return apiResponse;
    }

    private void saveFxRateResponseToDb(ExchangeRecordId recordId, FxRatesResponse apiResponse) {
        try {
            String responseAsJson = objectMapper.writeValueAsString(apiResponse);
            EcxcangeRecord ecxcangeRecord = EcxcangeRecord.builder()
                    .id(recordId)
                    .fxRatesResponse(responseAsJson).build();
            exchangeRecordsRepository.save(ecxcangeRecord);
        } catch (Throwable e) {
            log.error("Failed to save FX rates for {} to the database due to {}", recordId, e.getMessage(), e);
        }
    }
}