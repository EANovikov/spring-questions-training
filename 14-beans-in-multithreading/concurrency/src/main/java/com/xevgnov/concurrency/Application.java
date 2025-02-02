package com.xevgnov.concurrency;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;

import com.xevgnov.concurrency.service.RateCalculatorService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableAsync
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(TaskExecutor taskExecutor, ApplicationContext applicationContext) {
		//1st user runs the task
		taskExecutor.execute(() -> {
			RateCalculatorService user1Calculator = applicationContext.getBean(RateCalculatorService.class);
			user1Calculator.addToRate(10);
			user1Calculator.addToRate(9);
			user1Calculator.addToRate(8);
			log.info("Resulting rate is: {}", user1Calculator.getRate()); // should give 9 average rate
		});

		//2nd user runs the task
		taskExecutor.execute(() -> {
			RateCalculatorService user2Calculator = applicationContext.getBean(RateCalculatorService.class);
			user2Calculator.addToRate(100);
			user2Calculator.addToRate(50);
			user2Calculator.addToRate(75);
			log.info("Resulting rate is: {}", user2Calculator.getRate()); // should give 75 average rate
		});

		//3rd user runs the task
		taskExecutor.execute(() -> {
			RateCalculatorService user3Calculator = applicationContext.getBean(RateCalculatorService.class);
			user3Calculator.addToRate(5);
			user3Calculator.addToRate(4);
			user3Calculator.addToRate(5);
			user3Calculator.addToRate(4);
			log.info("Resulting rate is: {}", user3Calculator.getRate()); // should give 4.5 average rate
		});

		return args -> log.info("Done!");
	}

}
