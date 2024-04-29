package com.diverger.movies.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;


@Data
public class Film {
    private String title;
    private Integer episodeId;
    private String openingCrawl;
    private String director;
    private String producer;
    private String releaseDate;
    private Set<String> characters;
    private Set<String> planets;
    private Set<String> starships;
    private Set<String> vehicles;
    private Set<String> species;
    private LocalDateTime created;
    private LocalDateTime edited;
    private String url;
}