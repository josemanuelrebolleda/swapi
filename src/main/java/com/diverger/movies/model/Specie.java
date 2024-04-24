package com.diverger.movies.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.net.URL;


@Data
public class Specie {
    private String name;
    private String classification;
    private String designation;
    private int averageHeight;
    private Set<String> skinColors;
    private Set<String> hairColors;
    private Set<String> eyeColors;
    private int averageLifespan;
    private Planet homeworld;
    private String language;
    private Set<Person> people;
    private Set<Film> films;
    private LocalDateTime created;
    private LocalDateTime edited;
    private URL url;
}