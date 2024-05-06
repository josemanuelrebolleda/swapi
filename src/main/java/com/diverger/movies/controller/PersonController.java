package com.diverger.movies.controller;

import com.diverger.movies.service.PersonServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation(value = "Get person information by name", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Person information successfully obtained"),
            @ApiResponse(code = 404, message = "Person with name xxxx not found")
    })
    @GetMapping("/swapi-proxy/person-info")
    public ResponseEntity<String> getPersonInfoByName(@RequestParam String name) {
        return personServiceImpl.getPersonInfoByName(name);
    }
}