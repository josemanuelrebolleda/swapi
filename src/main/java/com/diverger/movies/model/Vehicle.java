package com.diverger.movies.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;


@Data
public class Vehicle {
    private String name;
    private String model;
    private String manufacturer;
    private Integer costInCredits;
    private Double length;
    private Integer maxAtmospheringSpeed;
    private Integer crew;
    private Integer passengers;
    private Integer cargoCapacity;
    private String consumables;
    private String vehicleClass;
    private Set<String> pilots;
    private Set<String> films;
    private LocalDateTime created;
    private LocalDateTime edited;
    private String url;
}