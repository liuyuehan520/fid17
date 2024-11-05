package com.fdi17.common.domain;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import springfox.documentation.spring.web.DocumentationCache;

@Configuration
@ConditionalOnClass(DispatcherServlet.class)
public class EasySwaggerConfig {
    @Autowired
    DocumentationCache documentationCache;
    @Autowired
    WebApplicationContext applicationContext;

    public EasySwaggerConfig() {
    }

    @Bean
    ScanInterface provideInit() {
        return new ScanInterface(documentationCache, applicationContext);
    }
}
