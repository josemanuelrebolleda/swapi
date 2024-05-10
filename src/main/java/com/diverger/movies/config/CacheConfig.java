package com.diverger.movies.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableCaching
public class CacheConfig {
    @Autowired
    protected RestTemplate restTemplate = new RestTemplate();

}