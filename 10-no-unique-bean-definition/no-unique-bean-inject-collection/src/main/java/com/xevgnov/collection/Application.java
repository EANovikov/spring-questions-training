package com.xevgnov.collection;

import java.util.Map;
import java.util.Set;

import com.xevgnov.collection.service.ReportProcessorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.xevgnov.collection.service.ReportService;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	@Bean
	CommandLineRunner commandLineRunner(ReportProcessorService reportProcessorService) {
		return args -> reportProcessorService.process("test report data");
	}

//	@Bean
//	CommandLineRunner commandLineRunner(Set<ReportService> reportServices) {
//	return args -> reportServices.forEach(rs->rs.printReport("test"));
//	}

	// Injecting Map<String, ReportService> can be even more handy
	// Spring is able to assume, that Map key is a spring name
	// and the keys will contains bean names: htmlReportService, textReportService, xmlReportService
	// @Bean
	// CommandLineRunner commandLineRunner(Map<String, ReportService> reportServices) {
	// 	return args -> reportServices.entrySet()
	// 			.forEach(entry -> {
	// 				System.out.println(entry.getKey());
	// 				entry.getValue().printReport("test");
	// 			});
	// }
}
