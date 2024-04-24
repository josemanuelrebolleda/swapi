package com.diverger.movies.service;

import com.diverger.movies.dto.PersonDTO;
import com.diverger.movies.model.Vehicle;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
@Data
public abstract class SwapiService<T> {
    @Value("${swapi.base_url}")
    protected String BASE_URL;
    protected String SERVICE_URL;
    protected RestTemplate restTemplate = new RestTemplate();

//    public SwapiService(String SERVICE_URL) {
//        this.SERVICE_URL = SERVICE_URL;
//    }

    public abstract T fetchDataByName(String name);
    public abstract T fetchDataByIndex(int index);
    public abstract T fetchDataByURL(URL url);

    public abstract ResponseEntity<String> formatDataForOutput(T data);
}