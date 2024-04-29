package com.diverger.movies.controller;

import com.diverger.movies.service.StarshipServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StarshipController {

    StarshipServiceImpl starshipServiceImpl;
    @Lazy   
    @Autowired
    public StarshipController (StarshipServiceImpl starshipServiceImpl) {
        this.starshipServiceImpl = starshipServiceImpl;
    }

    @GetMapping("/swapi-proxy/starship-info")
    public ResponseEntity<String> getStarshipInfo(@RequestParam String name) {
        return null;
    }
}