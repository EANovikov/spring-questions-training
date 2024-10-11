package com.xevgnov.spring.testing.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.orm.jpa.JpaSystemException;

import com.xevgnov.spring.testing.entity.EcxcangeRecord;
import com.xevgnov.spring.testing.entity.ExchangeRecordId;

@DataJpaTest(properties={"spring.jpa.hibernate.ddl-auto=create-drop"})
public class ExchangeRecordsRepositoryTest {

    @Autowired
    private ExchangeRecordsRepository exchangeRecordsRepository;

    @Test
    void testAddAndGetValidRecord() {
        // Given
        ExchangeRecordId exchangeRecordId = new ExchangeRecordId(
                "2024-10-11", "CAD", "JPY");
        EcxcangeRecord ecxcangeRecord = EcxcangeRecord.builder()
                .id(exchangeRecordId)
                .fxRatesResponse("mockResponse")
                .build();
        // When
        exchangeRecordsRepository.save(ecxcangeRecord);
        // Then
        Optional<EcxcangeRecord> actualRecord = exchangeRecordsRepository.findById(exchangeRecordId);
        assertThat(actualRecord).isPresent();
        assertThat(actualRecord.get()).isEqualTo(ecxcangeRecord);
    }

    @Test
    void testGetNonExistingRecordReturnsEmptyResponse() {
        // Given
        ExchangeRecordId exchangeRecordId = new ExchangeRecordId(
                "2024-10-11", "CAD", "JPY");
        // When
        Optional<EcxcangeRecord> actualRecord = exchangeRecordsRepository.findById(exchangeRecordId);
        // Then
        assertThat(actualRecord).isEmpty();
    }

    @Test
    void testAddInvalidRecordThrowsJpaSystemException() {
        // Given
        ExchangeRecordId exchangeRecordId = null;
        EcxcangeRecord ecxcangeRecord = EcxcangeRecord.builder()
                .id(exchangeRecordId)
                .fxRatesResponse("mockResponse")
                .build();
        // When
        assertThatThrownBy(() -> exchangeRecordsRepository.save(ecxcangeRecord))
                .isInstanceOf(JpaSystemException.class);
    }

}
