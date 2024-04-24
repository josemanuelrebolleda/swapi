package com.diverger.movies.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class SpecieDTO {
    @JsonProperty("name")
    private String name;
    @JsonProperty("classification")
    private String classification;
    @JsonProperty("designation")
    private String designation;
    @JsonProperty("average_height")
    private String averageHeight;
    @JsonProperty("skin_colors")
    private String skinColors;
    @JsonProperty("hair_colors")
    private String hairColors;
    @JsonProperty("eye_colors")
    private String eyeColors;
    @JsonProperty("average_lifespan")
    private String averageLifespan;
    @JsonProperty("homeworld")
    private String homeworld;
    @JsonProperty("language")
    private String language;
    @JsonProperty("people")
    private List<String> people;
    @JsonProperty("films")
    private List<String> films;
    @JsonProperty("created")
    private String created;
    @JsonProperty("edited")
    private String edited;
    @JsonProperty("url")
    private String url;
}