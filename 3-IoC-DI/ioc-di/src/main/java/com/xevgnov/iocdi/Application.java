package com.xevgnov.iocdi;

import java.beans.BeanProperty;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.xevgnov.iocdi.service.TemperatureService;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(
			@Qualifier("celsiusTemperatureService")
			// @Qualifier("fahrenheitTemperatureService")
			TemperatureService temperatureService) {
		double temperature = 32.0;
		temperatureService.print(temperature);
		System.out.printf("The temperature after conversion [%s]\n",
				temperatureService.convert(temperature));
		return args -> System.out.println(temperatureService + " work is completed");
	}
}
