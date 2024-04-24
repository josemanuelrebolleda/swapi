package com.diverger.movies.controller;

import com.diverger.movies.dto.PersonDTO;
import com.diverger.movies.model.Person;
import com.diverger.movies.service.SwapiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {
    @Lazy   
@Autowired

    private final SwapiService<Person> swapiService;

    @Lazy   
@Autowired

    public PersonController (SwapiService<Person> swapiService) {
        this.swapiService = swapiService;
    }

    @GetMapping("/swapi-proxy/person-info")
    public ResponseEntity<String> getPersonInfo(@RequestParam String name) {
        Person person = swapiService.fetchDataByName(name);
        return swapiService.formatDataForOutput(person);
    }
}