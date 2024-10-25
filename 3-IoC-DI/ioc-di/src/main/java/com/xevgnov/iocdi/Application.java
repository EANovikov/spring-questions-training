package com.xevgnov.iocdi;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.xevgnov.iocdi.domain.Temperature;
import com.xevgnov.iocdi.domain.TemperatureMode;
import com.xevgnov.iocdi.service.PrintService;
import com.xevgnov.iocdi.service.PrintServiceImpl;
import com.xevgnov.iocdi.service.TemperatureService;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	// Bean CommandLineRunner is added to IoC container
	// It has dependency to another bean implementing TemperatureService interface. 
	// We must specify either we inject celsiusTemperatureService or fahrenheitTemperatureService
/* 
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
*/
	@Bean
	CommandLineRunner commandLineRunner(
		    @Qualifier("celsiusTemperatureService")
			TemperatureService temperatureService) {
		double temperature = 32.0;
		temperatureService.print(temperature);
		Temperature convertedTemperature = temperatureService.convert(temperature);
		//Dependency injection via method invocation
		printService().print(convertedTemperature.getValue(), convertedTemperature.getMode());
		return args -> System.out.println(temperatureService + " work is completed");
	}

	@Bean 
	PrintService printService(){
        return new PrintServiceImpl();
	}
}
