package com.xevgnov.spring.testing.dto;

import java.io.Serializable;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FxRatesResponse implements Serializable {
    private Boolean success;
    private String terms;
    private String privacy;
    private Instant timestamp;
    private ZonedDateTime date;
    private String base;
    private Map<String, Double> rates;
    
}
