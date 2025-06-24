package com.xevgnov.spring.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import com.xevgnov.spring.di.service.*;

@Configuration
@ComponentScan("com.xevgnov.spring.di.service")
public class ApplicationConfig {
    
    @Bean
    public TimeService timeService(){
        return new TimeServiceImpl();
    }

// This is how to declare DateTimeService in java configuration
//    @Bean
//    public DateTimeService dateTimeService(DateService dateService, TimeService timeService){
//        return new DateTimeServiceImpl(dateService, timeService);
//    }
}
