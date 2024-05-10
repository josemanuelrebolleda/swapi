package com.diverger.movies.config;

import com.diverger.movies.mapper.PersonMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @Bean
    public PersonMapper personMapper() {
        return new PersonMapper();
    }
}