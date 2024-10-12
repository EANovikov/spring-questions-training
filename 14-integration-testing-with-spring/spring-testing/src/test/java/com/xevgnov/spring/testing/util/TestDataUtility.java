package com.xevgnov.spring.testing.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

public class TestDataUtility {
    
       public static final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("yyyy-MM-dd");


        public static Map<String, Double> getPriceHistory() {
        Map<String, Double> priceHistory = new LinkedHashMap<>();
        for (int i = 0; i < 7; i++) {
            double value = i % 2 == 0 ? 0.91 : 0.90;
            priceHistory.put(LocalDate.now().minusDays(i).format(DATE_PATTERN), value);
        }
        return priceHistory;
    }
}
