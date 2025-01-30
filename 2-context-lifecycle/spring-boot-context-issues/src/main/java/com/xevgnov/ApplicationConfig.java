package com.xevgnov;

import com.xevgnov.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

//    @Bean
    @Bean("dateTimeServiceV2")
    public DateTimeService dateTimeServiceImpl(DateService dateService, TimeService timeService) {
        return new DateTimeServiceImpl(dateService, timeService);
    }
}
