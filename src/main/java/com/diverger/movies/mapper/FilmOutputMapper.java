package com.diverger.movies.mapper;

import com.diverger.movies.model.Film;
import com.diverger.movies.model.FilmOutput;

public class FilmOutputMapper {
    public static FilmOutput mapFilmOutput(Film film) {
        return new FilmOutput(film.getTitle(), film.getReleaseDate());
    }
}
