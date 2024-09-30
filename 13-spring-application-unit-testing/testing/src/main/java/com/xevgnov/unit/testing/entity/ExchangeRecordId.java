package com.xevgnov.unit.testing.entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRecordId implements Serializable {

    private String date;
    private String sellCurrency;
    private String buyCurrency;

}
