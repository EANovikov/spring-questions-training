package com.xevgnov.scopes.external;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.xevgnov.scopes.config.ApplicationConfig;
import com.xevgnov.scopes.service.ApplicationRandomDateService;
import com.xevgnov.scopes.service.RandomDateService;
import com.xevgnov.scopes.service.SingletonRandomDateService;

// @ComponentScan(value = "com.xevgnov.scopes",
// excludeFilters = {
//     @ComponentScan.Filter(
//         type = FilterType.REGEX,
//         pattern =
//             "com.xevgnov.scopes.controller.*"),
//             @ComponentScan.Filter(
//                     type = FilterType.ASSIGNABLE_TYPE,
//                     value = {ApplicationConfig.class})
//         })
// //   excludeFilters = {@ComponentScan.Filter(
// //     type = FilterType.ASSIGNABLE_TYPE,
// //     value = {ApplicationConfig.class})
// //   })
//   @EnableAutoConfiguration
// @Configuration
@Configuration
@ComponentScan(basePackages = {"com.xevgnov.scopes.external", "com.xevgnov.scopes.service"})
@EnableWebMvc
@EnableAutoConfiguration
public class ApplicationConfigV2 {

    // @Bean
    // public RandomDateService applicationRandomDateService() {
    //     return new ApplicationRandomDateService();
    // }

    // @Bean
    // public RandomDateService singletonRandomDateService() {
    //     return new SingletonRandomDateService();
    // }

}
