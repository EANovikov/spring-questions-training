package com.xevgnov;

import jakarta.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import jakarta.servlet.ServletContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class JavaConfigWebApplicationInitializer implements WebApplicationInitializer {

    //1) use AnnotationConfigWebApplicationContext
    public void onStartup(ServletContext container) {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.registerShutdownHook();
        context.register(ApplicationConfig.class);
        context.setServletContext(container);
        ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", new DispatcherServlet(context));
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");
    }

    //2) use AnnotationConfigWebApplicationContext
//public void onStartup(ServletContext container) {
//
//    XmlWebApplicationContext context = new XmlWebApplicationContext();
//    context.registerShutdownHook();
//    context.setConfigLocation("/WEB-INF/application-config.xml");
//    context.setServletContext(container);
//    ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", new DispatcherServlet(context));
//    servlet.setLoadOnStartup(1);
//    servlet.addMapping("/");
//}
}
