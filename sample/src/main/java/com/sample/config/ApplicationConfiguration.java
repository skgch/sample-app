package com.sample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sample.helper.ApplicationDialect;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public ApplicationDialect ApricationDialect() {
        return new ApplicationDialect();
    }

}
