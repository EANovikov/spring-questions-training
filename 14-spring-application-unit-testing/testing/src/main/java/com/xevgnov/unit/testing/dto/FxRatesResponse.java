package com.xevgnov.unit.testing.dto;

import java.io.Serializable;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
private Instant timestamp;
private ZonedDateTime date;
private String base;
private Map<String, Double> rates;
//"{"success":true,"terms":"https://fxratesapi.com/legal/terms-conditions","privacy":"https://fxratesapi.com/legal/privacy-policy","date":1725390120.000000000,"timestamp":1725390120.000000000,"base":"PLN","rates":{"EUR":0.233565858}}"
// public FxRatesResponse(){}

// public FxRatesResponse(Boolean success, String terms, String privacy, Instant timestamp, ZonedDateTime date,
//         String base, Map<String, Double> rates) {
//     this.success = success;
//     this.terms = terms;
//     this.privacy = privacy;
//     this.timestamp = timestamp;
//     this.date = date;
//     this.base = base;
//     this.rates = rates;
// }


}
