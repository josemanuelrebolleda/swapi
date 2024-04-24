package com.diverger.movies.service;

import com.diverger.movies.dto.SpecieDTO;
import com.diverger.movies.dto.StarshipDTO;
import com.diverger.movies.exceptions.InvalidUrlException;
import com.diverger.movies.mapper.StarshipMapper;
import com.diverger.movies.model.Starship;
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
public class StarshipServiceImpl extends SwapiService <Starship> {
    @Lazy   
@Autowired

    private StarshipMapper starshipMapper;
    @Value("${json.name.field}")
    private String jsonNameField;
    @Value("${people.service_url}")
    private String serviceUrl;

    @PostConstruct
    public void init() {
        setSERVICE_URL(serviceUrl);
    }

    @Override
    @Cacheable("starshipByName")
    public Starship fetchDataByName(String name) {
        return null;
    }

    @Override
    @Cacheable("starshipByIndex")
    public Starship fetchDataByIndex(int index) {
        return null;
    }

    @Cacheable("starshipByURL")
    public Starship fetchDataByURL(URL url) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            StarshipDTO response = restTemplate.getForObject(url.toString(), StarshipDTO.class);
            if (response == null) {
                throw new InvalidUrlException("Planet: Response is null for URL: " + url);
            }
            return starshipMapper.dtoToStarship(response);
        } catch (Exception e) {
            throw new InvalidUrlException("Planet: Failed to fetch data from URL: " + url, e);
        }
    }


    public ResponseEntity<String> formatDataForOutput(Starship data) {
        return null;
    }
}