package com.diverger.movies.mapper;

import com.diverger.movies.exceptions.NullParameterException;
import com.diverger.movies.model.*;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@Component
public class PersonOutputMapper {
        public PersonOutput mapToPersonOutput(Person person) {
            if (person == null) {
                throw new NullParameterException("person for personOutput must not be null");
            }

            String name = person.getName();
            String birthYear = person.getBirth_year();
            String gender = person.getGender();
            String planetName = person.getHomeworld();

            String fastestVehicleDriven = person.getVehicles().stream()
                    .max(Comparator.comparingInt(Vehicle::getMaxAtmospheringSpeed))
                    .map(Vehicle::getName)
                    .orElse(null);

            List<FilmPersonOutput> filmsPerson = person.getFilms().stream()
                    .sorted(Comparator.comparing(Film::getRelease_date))
                    .map(film -> new FilmPersonOutput(film.getTitle(), film.getRelease_date()))
                    .collect(Collectors.toList());

            return new PersonOutput(name, birthYear, gender, planetName, fastestVehicleDriven, filmsPerson);
        }
    }

