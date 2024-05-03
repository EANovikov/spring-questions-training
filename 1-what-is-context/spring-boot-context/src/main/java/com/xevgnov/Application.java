package com.xevgnov;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import com.xevgnov.service.DateTimeService;
import com.xevgnov.service.DateTimeServiceImpl;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		// ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		// DateTimeService dateTimeService = context.getBean(DateTimeServiceImpl.class);
		// dateTimeService.printDateTime();
	}

	@Bean
	CommandLineRunner commandLineRunner(DateTimeService dateTimeService){
		return args -> dateTimeService.printDateTime();
	}

}
