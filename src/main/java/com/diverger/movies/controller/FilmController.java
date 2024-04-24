package com.diverger.movies.controller;

import com.diverger.movies.dto.FilmDTO;
import com.diverger.movies.model.Film;
import com.diverger.movies.service.SwapiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilmController {

    @Lazy   
@Autowired

    private final SwapiService<Film> swapiService;

    @Lazy   
@Autowired

    public FilmController (SwapiService<Film> swapiService) {
        this.swapiService = swapiService;
    }

    @GetMapping("/swapi-proxy/film-info")
    public ResponseEntity<String> getFilmInfo(@RequestParam String name) {
        Film film = swapiService.fetchDataByName(name);
        return swapiService.formatDataForOutput(film);
    }
}