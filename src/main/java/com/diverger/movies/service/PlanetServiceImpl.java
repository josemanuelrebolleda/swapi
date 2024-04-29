package com.diverger.movies.service;

import com.diverger.movies.dto.PlanetDTO;
import com.diverger.movies.mapper.PlanetMapper;
import com.diverger.movies.model.Planet;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PlanetServiceImpl extends SwapiService<Planet, PlanetDTO> {
    @Value("${json.name.field}")
    private String jsonNameField;

    @Value("${people.service_url}")
    private String serviceUrl;

    PlanetMapper planetMapper;

    @Lazy
    @Autowired
    public void setMappers(PlanetMapper planetMapper) {
        this.planetMapper = planetMapper;
    }

    @PostConstruct
    public void init() {
        setSERVICE_URL(serviceUrl);
    }

    @Override
    @Cacheable("planetByIndex")
    public Planet fetchDataByIndex(Integer index) {
        return null;
    }

    @Cacheable("planetByURL")
    public Planet fetchDataByURL(String url) {
        PlanetDTO response = super.fetchDataByURL(url, PlanetDTO.class);
        return planetMapper.dtoToPlanet(response);

    }

    public ResponseEntity<String> formatDataForOutput(Planet data) {
        return null;
    }
}