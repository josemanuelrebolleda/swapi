package com.diverger.movies.controller;

import com.diverger.movies.service.VehicleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VehicleController {

    VehicleServiceImpl vehicleServiceImpl;
    @Lazy   
    @Autowired
    public VehicleController(VehicleServiceImpl vehicleServiceImpl) {
        this.vehicleServiceImpl = vehicleServiceImpl;
    }

    @GetMapping("/swapi-proxy/vehicle-info")
    public ResponseEntity<String> getVehicleInfo(@RequestParam String name) {
        return null;
    }
}