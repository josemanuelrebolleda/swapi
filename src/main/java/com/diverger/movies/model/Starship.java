package com.diverger.movies.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;
import java.net.URL;


@Data
public class Starship {
    private String name;
    private String model;
    private String manufacturer;
    private int costInCredits;
    private double length;
    private int maxAtmospheringSpeed;
    private int crewMin;
    private int crewMax;
    private int passengers;
    private int cargoCapacity;
    private String consumables;
    private double hyperdriveRating;
    private int MGLT;
    private String starshipClass;
    private Set<Person> pilots;
    private Set<Film> films;
    private LocalDateTime created;
    private LocalDateTime edited;
    private URL url;
}