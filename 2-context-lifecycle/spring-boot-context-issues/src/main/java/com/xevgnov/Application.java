package com.xevgnov;

import com.xevgnov.service.TimeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.xevgnov.service.DateTimeService;

@SpringBootApplication
public class Application {

    // call SpringApplication.run to configure and create the context
	// if we need to run any logic right after application start, ApplicationRunner or CommandLineRunner bean can be used
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	//to run the logic on application startup
	@Bean
	CommandLineRunner commandLineRunner(DateTimeService dateTimeService) {
		return args -> dateTimeService.printDateTime();
	}

//	@Bean
//	CommandLineRunner commandLineRunner(TimeService timeService) {
//		return args -> timeService.printTime();
//	}

}
