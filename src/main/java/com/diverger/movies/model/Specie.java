package com.diverger.movies.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;


@Data
public class Specie {
    private String name;
    private String classification;
    private String designation;
    private Integer averageHeight;
    private Set<String> skinColors;
    private Set<String> hairColors;
    private Set<String> eyeColors;
    private Integer averageLifespan;
    private String homeworld;
    private String language;
    private Set<String> people;
    private Set<String> films;
    private LocalDateTime created;
    private LocalDateTime edited;
    private String url;
}