package com.xevgnov.unit.testing.dto;

import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExchangeStatistics {
    
    private String sellCurrency;
    private String buyCurrency;
    private String date;
    private Double middlePrice;
    private Double currentPrice;
    private Map<String, Double> priceHistory;

}
