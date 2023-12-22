package com.bolsaideas.springboot.app;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // Se le pasa como parametro la clase anotada con @SpringBootApplication
        return builder.sources(SpringBootDataJpaApplication.class);
    }

}
