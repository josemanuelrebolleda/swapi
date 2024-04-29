package com.diverger.movies.mapper;

import com.diverger.movies.exceptions.NullParameterException;
import com.diverger.movies.model.*;
import com.diverger.movies.service.FilmServiceImpl;
import com.diverger.movies.service.PlanetServiceImpl;
import com.diverger.movies.service.VehicleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
@Component
public class PersonOutputMapper {

    VehicleServiceImpl vehicleService;
    FilmServiceImpl filmService;
    PlanetServiceImpl planetService;
    @Autowired
    public void setVehicleService(VehicleServiceImpl vehicleService, FilmServiceImpl filmService, PlanetServiceImpl planetService) {
        this.vehicleService = vehicleService;
        this.filmService = filmService;
        this.planetService = planetService;
    }

    public PersonOutput mapToPersonOutput(Person person) {
        if (person == null) {
            throw new NullParameterException("person for personOutput must not be null");
        }

        String name = person.getName();
        String birthYear = person.getBirth_year();
        String gender = person.getGender();
        String planet = planetService.fetchDataByURL(person.getHomeworld()).getName();

        String fastestVehicleDriven = person.getVehicles().stream()
                .map(vehicleService::fetchDataByURL)
                .reduce((v1, v2) -> v1.getMaxAtmospheringSpeed() > v2.getMaxAtmospheringSpeed() ? v1 : v2)
                .map(Vehicle::getName).orElse("No vehicle driven");

        List<FilmOutput> filmsPerson = person.getFilms().stream()
                .map(filmService::fetchDataByURL)
                .sorted(Comparator.comparing(Film::getEpisodeId))
                .map(FilmOutputMapper::mapFilmOutput)
                .collect(Collectors.toList());

        return new PersonOutput(name, birthYear, gender, planet, fastestVehicleDriven, filmsPerson);
    }
}

