package com.diverger.movies.controller;

import com.diverger.movies.dto.StarshipDTO;
import com.diverger.movies.model.Person;
import com.diverger.movies.model.Starship;
import com.diverger.movies.service.StarshipServiceImpl;
import com.diverger.movies.service.SwapiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StarshipController {

    @Lazy   
@Autowired

    private final SwapiService<Starship> swapiService;

    @Lazy   
@Autowired

    public StarshipController (SwapiService<Starship> swapiService) {
        this.swapiService = swapiService;
    }

    @GetMapping("/swapi-proxy/starship-info")
    public ResponseEntity<String> getStarshipInfo(@RequestParam String name) {
        Starship starship = swapiService.fetchDataByName(name);
        return swapiService.formatDataForOutput(starship);
    }
}