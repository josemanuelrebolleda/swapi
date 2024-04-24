package com.diverger.movies.controller;

import com.diverger.movies.dto.SpecieDTO;
import com.diverger.movies.model.Specie;
import com.diverger.movies.service.SwapiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpecieController {

    private final SwapiService<Specie> swapiService;

    @Lazy   
@Autowired

    public SpecieController(SwapiService<Specie> swapiService) {
        this.swapiService = swapiService;
    }

    @GetMapping("/swapi-proxy/specie-info")
    public ResponseEntity<String> getSpecieInfo(@RequestParam String name) {
        Specie specie = swapiService.fetchDataByName(name);
        return swapiService.formatDataForOutput(specie);
    }
}