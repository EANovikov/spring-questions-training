package com.xevgnov.simple.proxy.demo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.xevgnov.simple.proxy.demo.dto.Temperature;
import com.xevgnov.simple.proxy.demo.service.TemperatureService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class ProxyDemoApplication {

	//Proxy design pattern is used to provide a surrogate or placeholder object, which references an underlying object.
	public static void main(String[] args) {
		SpringApplication.run(ProxyDemoApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(
			// Uncomment the following line to use the proxy class
			// @Qualifier("celsiusTemperatureService") TemperatureService temperatureService) {
			@Qualifier("fahrenheitTemperatureService") TemperatureService temperatureService) {
		Temperature temperature = temperatureService.getCurrentTemperatue();
		log.info("Current temperature is: {}", temperature);
		return args -> {
			System.out.println("Done!");
		};
	}

}
