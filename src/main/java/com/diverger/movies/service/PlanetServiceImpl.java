package com.diverger.movies.service;

import com.diverger.movies.dto.FilmDTO;
import com.diverger.movies.dto.PlanetDTO;
import com.diverger.movies.exceptions.InvalidUrlException;
import com.diverger.movies.mapper.FilmMapper;
import com.diverger.movies.mapper.PlanetMapper;
import com.diverger.movies.model.Planet;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URL;

@Service
public class PlanetServiceImpl extends SwapiService<Planet> {
    @Value("${json.name.field}")
    private String jsonNameField;

    @Value("${people.service_url}")
    private String serviceUrl;

    @Lazy   
@Autowired

    PlanetMapper planetMapper;

    @PostConstruct
    public void init() {
        setSERVICE_URL(serviceUrl);
    }
    @Override
    @Cacheable("planetByName")
    public Planet fetchDataByName(String name) {
        return null;
    }

    @Override
    @Cacheable("planetByIndex")
    public Planet fetchDataByIndex(int index) {
        return null;
    }

    @Cacheable("planetByURL")
    public Planet fetchDataByURL(URL url) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            PlanetDTO response = restTemplate.getForObject(url.toString(), PlanetDTO.class);
            if (response == null) {
                throw new InvalidUrlException("Planet: Response is null for URL: " + url);
            }
            return planetMapper.dtoToPlanet(response);
        } catch (Exception e) {
            throw new InvalidUrlException("Planet: Failed to fetch data from URL: " + url, e);
        }

    }

    public ResponseEntity<String> formatDataForOutput(Planet data) {
        return null;
    }
}