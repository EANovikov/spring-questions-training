package com.xevgnov.spring.testing.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeStatistics {
    
    private String sellCurrency;
    private String buyCurrency;
    private String date;
    private Double averagePrice;
    private Double currentPrice;
    private Map<String, Double> priceHistory;

}
