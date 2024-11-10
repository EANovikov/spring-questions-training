package com.xevgnov.autowire.circular.problem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import com.xevgnov.autowire.circular.problem.dto.Order;
import com.xevgnov.autowire.circular.problem.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(OrderService orderService) {
		Order order = Order.builder()
				.clientEmail("john.doe@gmail.com")
				.deliveryAddress("Wroclaw, Plac Konstytucji 3 Maja")
				.dishes(List.of("Pizza", "Chips", "Gyros"))
				.paymentId(UUID.randomUUID()).build();
		orderService.placeOrder(order);
		return args -> log.info("Done!");
	}
}
