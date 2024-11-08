package com.xevgnov.scopes;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.xevgnov.scopes.config.ApplicationConfig;
import com.xevgnov.scopes.external.ApplicationConfigV2;

@SpringBootConfiguration
public class Application {

	public static void main(String[] args) {
		SpringApplicationBuilder parentBuilder = new SpringApplicationBuilder(Application.class)
				.web(WebApplicationType.NONE);
		parentBuilder.run(args);

		parentBuilder.child(ApplicationConfigV2.class)
				.web(WebApplicationType.SERVLET)
				.properties("server.port=8081")
				.run(args);
		parentBuilder.child(ApplicationConfig.class)
				.web(WebApplicationType.SERVLET)
				.properties("server.port=8080")
				.run(args);
	}

}
