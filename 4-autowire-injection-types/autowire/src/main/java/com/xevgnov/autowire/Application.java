package com.xevgnov.autowire;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.xevgnov.autowire.domain.Order;
import com.xevgnov.autowire.service.RestaurantService;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	@Bean
	CommandLineRunner commandLineRunner(RestaurantService restaurantService){
		return args -> restaurantService.makeOrder(Order.builder().build());
	}
}
