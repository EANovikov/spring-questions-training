// package com.xevgnov.scopes;

// import org.springframework.boot.SpringApplication;
// import org.springframework.boot.SpringBootConfiguration;
// import org.springframework.boot.WebApplicationType;
// import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletRegistrationBean;
// import org.springframework.boot.builder.SpringApplicationBuilder;
// import org.springframework.boot.web.servlet.ServletRegistrationBean;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.ComponentScan;
// import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
// import org.springframework.web.servlet.DispatcherServlet;

// import com.xevgnov.scopes.config.ApplicationConfig;
// import com.xevgnov.scopes.external.ApplicationConfigV2;

// @SpringBootApplication(scanBasePackages="com.xevgnov.scopes.config")
// public class ApplicationV2 {

// 	public static void main(String[] args) {
// 		SpringApplication.run(ApplicationV2.class, args);
// 	}

// 	@Bean
//     public ServletRegistrationBean external() {
//         AnnotationConfigWebApplicationContext secondApplicationContext = new AnnotationConfigWebApplicationContext();
//         secondApplicationContext.register(ApplicationConfigV2.class);

//         DispatcherServlet dispatcherServlet = new DispatcherServlet();
//         dispatcherServlet.setApplicationContext(secondApplicationContext);

//         DispatcherServletRegistrationBean servletRegistrationBean = new DispatcherServletRegistrationBean(dispatcherServlet, "/v2/*");
//         servletRegistrationBean.setName("external");

//         return servletRegistrationBean;
//     }

// }
