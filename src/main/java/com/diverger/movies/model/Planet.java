package com.diverger.movies.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.net.URL;
import java.util.Set;

@Data
public class Planet {
    private String name;
    private int RotationPeriod;
    private int OrbitalPeriod;
    private int diameter;
    private String climate;
    private String gravity;
    private String terrain;
    private int SurfaceWater;
    private long population;
    private Set<Person> residents;
    private Set<Film> films;
    private LocalDateTime created;
    private LocalDateTime edited;
    private URL url;
}