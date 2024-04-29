package com.diverger.movies.model;

import java.util.List;

public record PersonOutput(String name, String birth_year, String gender, String planet_name, String fastest_vehicle_driven, List<FilmOutput> films) {
}