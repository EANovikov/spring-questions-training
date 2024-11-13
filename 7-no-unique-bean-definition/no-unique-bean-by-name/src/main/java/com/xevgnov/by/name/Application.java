package com.xevgnov.by.name;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

import com.xevgnov.by.name.service.HtmlReportService;
import com.xevgnov.by.name.service.ReportService;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	// 3 inject bean by name
	// better alternative to explicitly providing the type
	// HtmlReportService htmlReportService
	// which creates more coupling between the classes
	@Bean
	CommandLineRunner commandLineRunner(
		ReportService htmlReportService) {
		return args -> htmlReportService.printReport("test");
	}
}
