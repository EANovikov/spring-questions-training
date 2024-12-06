package com.xevgnov.scopes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletRegistrationBean;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.xevgnov.scopes.config.ApplicationConfig;
import com.xevgnov.scopes.external.ApplicationConfigV2;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;

@SpringBootApplication(scanBasePackages="com.xevgnov.scopes.config")
public class ApplicationV3 implements ServletContextInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationV3.class, args);
	}


    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ctx
        = new AnnotationConfigWebApplicationContext();
      ctx.register(ApplicationConfigV2.class);
      ctx.setServletContext(servletContext);

       DispatcherServlet dispatcherServlet = new DispatcherServlet();
        dispatcherServlet.setApplicationContext(ctx);
        
      ServletRegistration.Dynamic servlet = servletContext.addServlet(
        "external", dispatcherServlet);
      servlet.setLoadOnStartup(2);
      servlet.addMapping("/v2/*");
      
    }

    

	// @Bean
    // public ServletRegistrationBean external() {
    //     AnnotationConfigWebApplicationContext secondApplicationContext = new AnnotationConfigWebApplicationContext();
    //     secondApplicationContext.register(ApplicationConfigV2.class);

    //     DispatcherServlet dispatcherServlet = new DispatcherServlet();
    //     dispatcherServlet.setApplicationContext(secondApplicationContext);

    //     DispatcherServletRegistrationBean servletRegistrationBean = new DispatcherServletRegistrationBean(dispatcherServlet, "/v2/*");
    //     servletRegistrationBean.setName("external");

    //     return servletRegistrationBean;
    // }

}
