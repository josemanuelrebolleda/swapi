package com.diverger.movies.service;

import com.diverger.movies.exceptions.InvalidUrlException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
@Data
public abstract class SwapiService<T, D> {
    @Value("${swapi.base_url}")
    protected String BASE_URL;
    protected String SERVICE_URL;
    @Value("${json.count.field}")
    String COUNT = "count";
    protected RestTemplate restTemplate = new RestTemplate();

    public abstract T fetchDataByIndex(Integer index);
    public D fetchDataByURL(String url, Class<D> classData) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            D response = restTemplate.getForObject(url, classData);
            if (response == null) {
                throw new InvalidUrlException(classData.getSimpleName() + ": Response is null for URL: " + url);
            }
            return response;
        } catch (Exception e) {
            throw new InvalidUrlException(classData.getSimpleName() + ": Failed to fetch data from URL: " + url, e);
        }
    }
    public abstract ResponseEntity<String> formatDataForOutput(T data);
}