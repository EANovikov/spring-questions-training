package com.xevgnov.proxy.bean.post.processor.use;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;


import com.xevgnov.proxy.bean.post.processor.use.dto.Temperature;
import com.xevgnov.proxy.bean.post.processor.use.service.TemperatureService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class BeanPostProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeanPostProcessorApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(TemperatureService temperatureService) {
		Temperature temperature = temperatureService.getCurrentTemperatue();
		log.info("Current temperature is: {}", temperature);
		return args -> {
			System.out.println("Done!");
		};
	}

}
