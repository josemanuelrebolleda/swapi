package com.diverger.movies.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class Planet {
    private String name;
    private Integer RotationPeriod;
    private Integer OrbitalPeriod;
    private Integer diameter;
    private String climate;
    private String gravity;
    private String terrain;
    private Double SurfaceWater;
    private long population;
    private Set<String> residents;
    private Set<String> films;
    private LocalDateTime created;
    private LocalDateTime edited;
    private String url;
}