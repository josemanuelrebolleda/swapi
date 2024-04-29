package com.diverger.movies.controller;

import com.diverger.movies.service.FilmServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilmController {

    FilmServiceImpl filmServiceImpl;
    @Lazy   
    @Autowired
    public FilmController (FilmServiceImpl filmServiceImpl) {
        this.filmServiceImpl = filmServiceImpl;
    }

    @GetMapping("/swapi-proxy/film-info")
    public ResponseEntity<String> getFilmInfo(@RequestParam String name) {
        return null;
    }
}