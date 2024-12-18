package com.xevgnov.concurrency;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;

import com.xevgnov.concurrency.service.RateCalculatorService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(TaskExecutor taskExecutor, RateCalculatorService rateCalculatorService) {
		// for (int i = 0; i < 1; i++) { // no issue for single run

		for (int i = 0; i < 10; i++) {
			taskExecutor.execute(() -> {
				rateCalculatorService.addToRate(10);
				rateCalculatorService.addToRate(9);
				rateCalculatorService.addToRate(8);
				rateCalculatorService.addToRate(7);
				rateCalculatorService.getRate();
			});
		}
		return args -> log.info("Done!");
	}

}
