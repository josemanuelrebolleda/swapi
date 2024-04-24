package com.diverger.movies.service;

import com.diverger.movies.dto.FilmDTO;
import com.diverger.movies.exceptions.InvalidUrlException;
import com.diverger.movies.mapper.FilmMapper;
import com.diverger.movies.model.Film;
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
public class FilmServiceImpl extends SwapiService<Film>  {
    @Value("${json.name.field}")
    private String jsonNameField;
    @Value("${people.service_url}")
    private String serviceUrl;

    private FilmMapper filmMapper;

    @Lazy   
    @Autowired
    public void setFilmMapper(FilmMapper filmMapper) {
        this.filmMapper = filmMapper;
    }

    @PostConstruct
    public void init() {
        setSERVICE_URL(serviceUrl);
    }
    @Override
    @Cacheable("filmByName")
    public Film fetchDataByName(String name) {
        return null;
    }

    @Override
    @Cacheable("filmByName")
    public Film fetchDataByIndex(int index) {
        return null;
    }


    @Cacheable("filmByURL")
    public Film fetchDataByURL(URL url) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            FilmDTO response = restTemplate.getForObject(url.toString(), FilmDTO.class);
            if (response == null) {
                throw new InvalidUrlException("Film: Response is null for URL: " + url);
            }
            return filmMapper.dtoToFilm(response);
        } catch (Exception e) {
            throw new InvalidUrlException("Film: Failed to fetch data from URL: " + url, e);
        }
    }
    public ResponseEntity<String> formatDataForOutput(Film data) {
        return null;
    }
}