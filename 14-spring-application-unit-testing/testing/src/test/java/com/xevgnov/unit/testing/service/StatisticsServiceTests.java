package com.xevgnov.unit.testing.service;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.xevgnov.unit.testing.dto.ExchangeStatistics;
import com.xevgnov.unit.testing.dto.FxRatesResponse;

@ExtendWith(MockitoExtension.class)
public class StatisticsServiceTests {
    
@Mock
private CurrencyService currencyService;

@InjectMocks    
private StatisticsService statisticsService;

@Test
public void test(){
    //Given
    FxRatesResponse fxRatesResponse = new FxRatesResponse();
    //todo set all fields
     when(currencyService.getFxRateForDate(anyString(), anyString(), anyString()))
     .thenReturn(fxRatesResponse);
    //When
   ExchangeStatistics statisticsResult = statisticsService.getStatistics("EUR", "USD");

   //Then

}

}
