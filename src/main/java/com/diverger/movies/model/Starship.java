package com.diverger.movies.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;


@Data
public class Starship {
    private String name;
    private String model;
    private String manufacturer;
    private Integer costInCredits;
    private Double length;
    private Integer maxAtmospheringSpeed;
    private Integer crewMin;
    private Integer crewMax;
    private Integer passengers;
    private Integer cargoCapacity;
    private String consumables;
    private Double hyperdriveRating;
    private Integer MGLT;
    private String starshipClass;
    private Set<String> pilots;
    private Set<String> films;
    private LocalDateTime created;
    private LocalDateTime edited;
    private String url;
}