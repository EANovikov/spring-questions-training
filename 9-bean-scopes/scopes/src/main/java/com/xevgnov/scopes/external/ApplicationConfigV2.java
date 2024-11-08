package com.xevgnov.scopes.external;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan("com.xevgnov.scopes")
@EnableAutoConfiguration
@Configuration(proxyBeanMethods = false)
public class ApplicationConfigV2 {

}
