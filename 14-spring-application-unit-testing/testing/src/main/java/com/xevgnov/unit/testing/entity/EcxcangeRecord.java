package com.xevgnov.unit.testing.entity;

import com.xevgnov.unit.testing.dto.FxRatesResponse;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class EcxcangeRecord {
    @EmbeddedId
    private ExchangeRecordId id;
    private String fxRatesResponse;

}
