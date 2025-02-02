package com.xevgnov;

import com.xevgnov.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ApplicationConfig {

// Uncomment to see the context fails to start
//    @Bean
// Uncomment to see issue on attempt to get DateTimeService bean by type.
//    @Bean("dateTimeServiceImplV2")
    public DateTimeService dateTimeServiceImpl(DateService dateService, TimeService timeService) {
        return new DateTimeServiceImpl(dateService, timeService);
    }
}
