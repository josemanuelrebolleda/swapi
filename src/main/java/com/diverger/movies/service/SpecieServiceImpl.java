package com.diverger.movies.service;

import com.diverger.movies.dto.SpecieDTO;
import com.diverger.movies.mapper.FilmMapper;
import com.diverger.movies.mapper.SpecieMapper;
import com.diverger.movies.model.Specie;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SpecieServiceImpl extends SwapiService <Specie, SpecieDTO> {
    @Value("${json.name.field}")
    private String jsonNameField;

    @Value("${people.service_url}")
    private String serviceUrl;

    FilmMapper filmMapper;
    SpecieMapper specieMapper;

    @Lazy
    @Autowired
    public void setMappers(FilmMapper filmMapper, SpecieMapper specieMapper) {
        this.filmMapper = filmMapper;
        this.specieMapper = specieMapper;
    }

    @PostConstruct
    public void init() {
        setSERVICE_URL(serviceUrl);
    }


    @Override
    @Cacheable("specieByIndex")
    public Specie fetchDataByIndex(Integer index) {
        return null;
    }

    @Cacheable("specieByURL")
    public Specie fetchDataByURL(String url) {
        SpecieDTO response = super.fetchDataByURL(url, SpecieDTO.class);
        return specieMapper.dtoToSpecie(response);
    }

    public ResponseEntity<String> formatDataForOutput(Specie data) {
        return null;
    }
}