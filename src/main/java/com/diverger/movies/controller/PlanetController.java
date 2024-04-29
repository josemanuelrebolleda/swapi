package com.diverger.movies.controller;

import com.diverger.movies.service.PlanetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlanetController {

    PlanetServiceImpl planetServiceImpl;
    @Lazy   
    @Autowired
    public PlanetController(PlanetServiceImpl planetServiceImpl) {
        this.planetServiceImpl = planetServiceImpl;
    }

    @GetMapping("/swapi-proxy/planet-info")
    public ResponseEntity<String> getPlanetInfo(@RequestParam String name) {
        return null;
    }
}