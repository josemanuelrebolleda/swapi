package com.diverger.movies.model;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record PersonOutput(String name, String birth_year, String gender, String planet_name, String fastest_vehicle_driven, List<FilmPersonOutput> films) {
}