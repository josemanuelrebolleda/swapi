package com.diverger.movies.service;

import com.diverger.movies.dto.PlanetDTO;
import com.diverger.movies.dto.SpecieDTO;
import com.diverger.movies.exceptions.InvalidUrlException;
import com.diverger.movies.mapper.FilmMapper;
import com.diverger.movies.mapper.SpecieMapper;
import com.diverger.movies.model.Specie;
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
public class SpecieServiceImpl extends SwapiService <Specie> {
    @Lazy   
@Autowired

    private SpecieMapper specieMapper;
    @Value("${json.name.field}")
    private String jsonNameField;

    @Value("${people.service_url}")
    private String serviceUrl;

    @Lazy   
@Autowired

    FilmMapper filmMapper;

    @PostConstruct
    public void init() {
        setSERVICE_URL(serviceUrl);
    }

    @Override
    @Cacheable("specieByName")
    public Specie fetchDataByName(String name) {
        return null;
    }

    @Override
    @Cacheable("specieByIndex")
    public Specie fetchDataByIndex(int index) {
        return null;
    }

    @Cacheable("specieByURL")
    public Specie fetchDataByURL(URL url) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            SpecieDTO response = restTemplate.getForObject(url.toString(), SpecieDTO.class);
            if (response == null) {
                throw new InvalidUrlException("Planet: Response is null for URL: " + url);
            }
            return specieMapper.dtoToSpecie(response);
        } catch (Exception e) {
            throw new InvalidUrlException("Planet: Failed to fetch data from URL: " + url, e);
        }
    }

    public ResponseEntity<String> formatDataForOutput(Specie data) {
        return null;
    }
}