package com.xevgnov.unit.testing.entity;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
public class ExchangeRecordId implements Serializable {
    
    private String date;
    private String sellCurrency;
    private String buyCurrency;

}
