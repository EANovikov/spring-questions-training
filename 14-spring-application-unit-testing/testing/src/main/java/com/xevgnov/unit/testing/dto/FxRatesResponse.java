package com.xevgnov.unit.testing.dto;

import java.io.Serializable;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true) 
public class FxRatesResponse implements Serializable {
/*
    "success": true,
    "terms": "https://fxratesapi.com/legal/terms-conditions",
    "privacy": "https://fxratesapi.com/legal/privacy-policy",
    "timestamp": 1723007580,
    "date": "2024-08-07T05:13:00.000Z",
    "base": "PLN",
    "rates": {
        "EUR": 0.232432063,
        "USD": 0.253597242
    }
*/ 
private Boolean success;
private String terms;
private String privacy;
private ZonedDateTime date;
private Instant timestamp;
private String base;
private Map<String, Double> rates;
}
