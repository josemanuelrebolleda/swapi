package com.diverger.movies.service;

import com.diverger.movies.dto.FilmDTO;
import com.diverger.movies.mapper.FilmMapper;
import com.diverger.movies.model.Film;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FilmServiceImpl extends SwapiService<Film, FilmDTO>  {
    @Value("${json.name.field}")
    private String jsonNameField;
    @Value("${people.service_url}")
    private String serviceUrl;

    FilmMapper filmMapper;

    @Lazy   
    @Autowired
    public void setMappers(FilmMapper filmMapper) {
        this.filmMapper = filmMapper;
    }

    @PostConstruct
    public void init() {
        setSERVICE_URL(serviceUrl);
    }

    @Override
    @Cacheable("filmByIndex")
    public Film fetchDataByIndex(Integer index) {
        return null;
    }


    @Cacheable("filmByURL")
    public Film fetchDataByURL(String url) {
        FilmDTO response = super.fetchDataByURL(url, FilmDTO.class);
        return filmMapper.dtoToFilm(response);
    }
    public ResponseEntity<String> formatDataForOutput(Film data) {
        return null;
    }
}