package com.xevgnov.proxy.bean.post.processor.use;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.xevgnov.simple.proxy.demo.dto.Temperature;
import com.xevgnov.simple.proxy.demo.service.TemperatureService;

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
