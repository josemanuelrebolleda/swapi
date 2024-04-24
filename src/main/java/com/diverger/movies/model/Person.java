package com.diverger.movies.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.net.URL;


@Data
public class Person {
    private String name;
    private int height;
    private int mass;
    private String hair_color;
    private String skin_color;
    private String eye_color;
    private String birth_year;
    private String gender;
    private String homeworld;
    private Set<Film> films;
    private Set<Specie> species;
    private Set<Vehicle> vehicles;
    private Set<Starship> starships;
    private LocalDateTime created;
    private LocalDateTime edited;
    private URL url;
}