package com.diverger.movies.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;
import java.net.URL;


@Data
public class Vehicle {
    private String name;
    private String model;
    private String manufacturer;
    private int costInCredits;
    private Double length;
    private int maxAtmospheringSpeed;
    private int crew;
    private int passengers;
    private int cargoCapacity;
    private String consumables;
    private String vehicleClass;
    private Set<Person> pilots;
    private Set<Film> films;
    private LocalDateTime created;
    private LocalDateTime edited;
    private URL url;
}