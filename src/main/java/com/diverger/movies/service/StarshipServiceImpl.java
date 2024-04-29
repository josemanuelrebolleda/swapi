package com.diverger.movies.service;

import com.diverger.movies.dto.StarshipDTO;
import com.diverger.movies.mapper.StarshipMapper;
import com.diverger.movies.model.Starship;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StarshipServiceImpl extends SwapiService <Starship, StarshipDTO> {

    @Value("${json.name.field}")
    private String jsonNameField;
    @Value("${people.service_url}")
    private String serviceUrl;

    private StarshipMapper starshipMapper;
    @Lazy
    @Autowired
    public void setMappers(StarshipMapper starshipMapper) {
        this.starshipMapper = starshipMapper;
    }

    @PostConstruct
    public void init() {
        setSERVICE_URL(serviceUrl);
    }


    @Override
    @Cacheable("starshipByIndex")
    public Starship fetchDataByIndex(Integer index) {
        return null;
    }

    @Cacheable("starshipByURL")
    public Starship fetchDataByURL(String url) {
        StarshipDTO response = super.fetchDataByURL(url, StarshipDTO.class);
        return starshipMapper.dtoToStarship(response);
    }


    public ResponseEntity<String> formatDataForOutput(Starship data) {
        return null;
    }
}