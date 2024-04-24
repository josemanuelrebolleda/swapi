package com.diverger.movies.controller;

import com.diverger.movies.dto.VehicleDTO;
import com.diverger.movies.model.Vehicle;
import com.diverger.movies.service.SwapiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VehicleController {

    private final SwapiService<Vehicle> swapiService;

    @Lazy   
@Autowired

    public VehicleController(SwapiService<Vehicle> swapiService) {
        this.swapiService = swapiService;
    }

    @GetMapping("/swapi-proxy/vehicle-info")
    public ResponseEntity<String> getVehicleInfo(@RequestParam String name) {
        Vehicle vehicle = swapiService.fetchDataByName(name);
        return swapiService.formatDataForOutput(vehicle);
    }
}