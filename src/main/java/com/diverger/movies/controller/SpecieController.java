package com.diverger.movies.controller;

import com.diverger.movies.service.SpecieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpecieController {

    SpecieServiceImpl specieServiceImpl;
    @Lazy   
    @Autowired
    public SpecieController(SpecieServiceImpl specieServiceImpl) {
        this.specieServiceImpl = specieServiceImpl;
    }

    @GetMapping("/swapi-proxy/specie-info")
    public ResponseEntity<String> getSpecieInfo(@RequestParam String name) {
        return null;
    }
}