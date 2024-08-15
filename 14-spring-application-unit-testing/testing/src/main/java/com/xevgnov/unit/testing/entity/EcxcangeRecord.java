package com.xevgnov.unit.testing.entity;

import com.xevgnov.unit.testing.dto.FxRatesResponse;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@Table(name = "ECXCANGE_RECORD")
@AllArgsConstructor
@NoArgsConstructor
public class EcxcangeRecord {
    @EmbeddedId
    @Column(name = "ID")
    private ExchangeRecordId id;
    @Column(name = "FX_RATES_RESPONSE")
    private String fxRatesResponse;

}
