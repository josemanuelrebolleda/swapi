package com.diverger.movies.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;


@Data
public class Person {
    private String name;
    private Integer height;
    private Double mass;
    private String hair_color;
    private String skin_color;
    private String eye_color;
    private String birth_year;
    private String gender;
    private String homeworld;
    private Set<String> films;
    private Set<String> species;
    private Set<String> vehicles;
    private Set<String> starships;
    private LocalDateTime created;
    private LocalDateTime edited;
    private String url;
}