package com.diverger.movies.controller;

import com.diverger.movies.dto.PlanetDTO;
import com.diverger.movies.model.Planet;
import com.diverger.movies.service.SwapiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlanetController {

    private final SwapiService<Planet> swapiService;

    @Lazy   
@Autowired

    public PlanetController(SwapiService<Planet> swapiService) {
        this.swapiService = swapiService;
    }

    @GetMapping("/swapi-proxy/planet-info")
    public ResponseEntity<String> getPlanetInfo(@RequestParam String name) {
        Planet planet = swapiService.fetchDataByName(name);
        return swapiService.formatDataForOutput(planet);
    }
}