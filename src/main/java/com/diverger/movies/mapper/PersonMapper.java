package com.diverger.movies.mapper;

import com.diverger.movies.dto.PersonDTO;
import com.diverger.movies.exceptions.InvalidUrlException;
import com.diverger.movies.exceptions.MalformedUrlException;
import com.diverger.movies.model.Person;
import com.diverger.movies.service.FilmServiceImpl;
import com.diverger.movies.service.SpecieServiceImpl;
import com.diverger.movies.service.StarshipServiceImpl;
import com.diverger.movies.service.VehicleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

@Component
public class PersonMapper {

    private FilmServiceImpl filmService;
    private SpecieServiceImpl speciesService;
    private VehicleServiceImpl vehiclesService;
    private StarshipServiceImpl starshipsService;

    @Lazy   
@Autowired

    public void setFilmService(FilmServiceImpl filmService) {
        this.filmService = filmService;
    }

    @Lazy
    @Autowired

    public void setSpeciesService(SpecieServiceImpl speciesService) {
        this.speciesService = speciesService;
    }

    @Lazy   
    @Autowired

    public void setVehiclesService(VehicleServiceImpl vehiclesService) {
        this.vehiclesService = vehiclesService;
    }

    @Lazy   
    @Autowired

    public void setStarshipsService(StarshipServiceImpl starshipsService) {
        this.starshipsService = starshipsService;
    }

    public Person dtoToPerson(PersonDTO personDTO) {
        Person person = new Person();
        person.setName(personDTO.getName());
        person.setHeight(Integer.parseInt(personDTO.getHeight()));
        person.setMass(Integer.parseInt(personDTO.getMass()));
        person.setHair_color(personDTO.getHairColor());
        person.setSkin_color(personDTO.getSkinColor());
        person.setEye_color(personDTO.getEyeColor());
        person.setBirth_year(personDTO.getBirthYear());
        person.setGender(personDTO.getGender());
        person.setHomeworld(personDTO.getHomeworld());
        person.setCreated(LocalDateTime.ofInstant(Instant.parse(personDTO.getCreated()), ZoneId.systemDefault()));
        person.setEdited(LocalDateTime.ofInstant(Instant.parse(personDTO.getEdited()), ZoneId.systemDefault()));


        try {
            person.setFilms(personDTO.getFilms().stream()
                    .map(s -> {
                        try {
                            return new URL(s);
                        } catch (MalformedURLException e) {
                            throw new MalformedUrlException("Malformed URL in getFilms: " + s, e);
                        }
                    })
                    .map(filmService::fetchDataByURL)
                    .collect(Collectors.toSet()));
        } catch (InvalidUrlException e) {
            throw new InvalidUrlException("Invalid URL in setFilms: ", e);
        }

        try {
            person.setSpecies(personDTO.getSpecies().stream()
                    .map(s -> {
                        try {
                            return new URL(s);
                        } catch (MalformedURLException e) {
                            throw new MalformedUrlException("Malformed URL in setSpecies: " + s, e);
                        }
                    })
                    .map(speciesService::fetchDataByURL)
                    .collect(Collectors.toSet()));
        } catch (InvalidUrlException e) {
            throw new InvalidUrlException("Invalid URL in setSpecies: ", e);
        }

        try {
            person.setVehicles(personDTO.getVehicles().stream()
                    .map(s -> {
                        try {
                            return new URL(s);
                        } catch (MalformedURLException e) {
                            throw new MalformedUrlException("Malformed URL in setVehicles: " + s, e);
                        }
                    })
                    .map(vehiclesService::fetchDataByURL)
                    .collect(Collectors.toSet()));
        } catch (InvalidUrlException e) {
            throw new InvalidUrlException("Invalid URL in setVehicles: ", e);
        }

        try {
            person.setStarships(personDTO.getStarships().stream()
                    .map(s -> {
                        try {
                            return new URL(s);
                        } catch (MalformedURLException e) {
                            throw new MalformedUrlException("Malformed URL in setStarships: " + s, e);
                        }
                    })
                    .map(starshipsService::fetchDataByURL)
                    .collect(Collectors.toSet()));
        } catch (InvalidUrlException e) {
            throw new InvalidUrlException("Invalid URL in setStarships: ", e);
        }

        return person;
    }
}