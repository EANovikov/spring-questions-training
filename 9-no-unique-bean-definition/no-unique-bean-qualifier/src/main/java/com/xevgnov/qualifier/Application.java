package com.xevgnov.qualifier;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

import com.xevgnov.qualifier.service.HtmlReportService;
import com.xevgnov.qualifier.service.ReportService;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(
		//1 qualifier usage 
		@Qualifier("htmlReportService") ReportService reportService) {
		return args -> reportService.printReport("test");
	}

	// @Bean
	// CommandLineRunner commandLineRunner(
	// 	//2 qualifier usage
	// 	// use @Qalifier in injection point and on the component you need to inject
	// 	@Qualifier("html") ReportService reportService) {
	// 	return args -> reportService.printReport("test");
	// }

}
