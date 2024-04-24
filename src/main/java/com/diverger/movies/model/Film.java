package com.diverger.movies.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.net.URL;


@Data
public class Film {
    private String title;
    private int episode_id;
    private String opening_crawl;
    private String director;
    private String producer;
    private String release_date;
    private List<Person> characters;
    private List<Planet> planets;
    private List<Starship> starships;
    private List<Vehicle> vehicles;
    private List<Specie> species;
    private LocalDateTime created;
    private LocalDateTime edited;
    private URL url;
}