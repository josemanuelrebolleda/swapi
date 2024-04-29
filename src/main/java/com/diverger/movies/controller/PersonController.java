package com.diverger.movies.controller;

import com.diverger.movies.service.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {
    PersonServiceImpl personServiceImpl;

    @Lazy
    @Autowired
    public PersonController (PersonServiceImpl personServiceImpl) {
        this.personServiceImpl = personServiceImpl;
    }

    @GetMapping("/swapi-proxy/person-info")
    public ResponseEntity<String> getPersonInfoByName(@RequestParam String name) {
        return personServiceImpl.getPersonInfoByName(name);
    }
}