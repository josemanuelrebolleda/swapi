package com.diverger.movies.mapper;

import com.diverger.movies.dto.SpecieDTO;
import com.diverger.movies.exceptions.InvalidUrlException;
import com.diverger.movies.exceptions.MalformedUrlException;
import com.diverger.movies.model.Specie;
import com.diverger.movies.service.FilmServiceImpl;
import com.diverger.movies.service.PersonServiceImpl;
import com.diverger.movies.service.PlanetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

@Component
public class SpecieMapper {

    private FilmServiceImpl filmService;
    private PersonServiceImpl personService;
    private PlanetServiceImpl planetService;

    @Lazy   
@Autowired

    public void setFilmService(FilmServiceImpl filmService) {
        this.filmService = filmService;
    }

    @Lazy   
@Autowired

    public void setPersonService(PersonServiceImpl personService) {
        this.personService = personService;
    }

    @Lazy   
@Autowired

    public void setPlanetService(PlanetServiceImpl planetService) {
        this.planetService = planetService;
    }

    public Specie dtoToSpecie(SpecieDTO specieDTO) {
        Specie specie = new Specie();
        specie.setName(specieDTO.getName());
        specie.setClassification(specieDTO.getClassification());
        specie.setDesignation(specieDTO.getDesignation());
        specie.setAverageHeight(Integer.parseInt(specieDTO.getAverageHeight()));
        specie.setAverageLifespan(Integer.parseInt(specieDTO.getAverageLifespan()));
        specie.setLanguage(specieDTO.getLanguage());
        specie.setSkinColors(new HashSet<>(Arrays.asList(specieDTO.getSkinColors().split(","))));
        specie.setHairColors(new HashSet<>(Arrays.asList(specieDTO.getHairColors().split(","))));
        specie.setEyeColors(new HashSet<>(Arrays.asList(specieDTO.getEyeColors().split(","))));
        specie.setCreated(LocalDateTime.ofInstant(Instant.parse(specieDTO.getCreated()), ZoneId.systemDefault()));
        specie.setEdited(LocalDateTime.ofInstant(Instant.parse(specieDTO.getEdited()), ZoneId.systemDefault()));

        try {
            specie.setUrl(new URL(specieDTO.getUrl()));
        } catch (MalformedURLException e) {
            throw new MalformedUrlException("Malformed URL in getUrl: " + specieDTO.getUrl(), e);
        }

        try {
            specie.setHomeworld(planetService.fetchDataByURL(new URL(specieDTO.getHomeworld())));
        } catch (MalformedURLException e) {
            throw new MalformedUrlException("Malformed URL in getHomeworld: " + specieDTO.getHomeworld(), e);
        }

        try {
            specie.setPeople(specieDTO.getPeople().stream()
                    .map(s -> {
                        try {
                            return new URL(s);
                        } catch (MalformedURLException e) {
                            throw new MalformedUrlException("Malformed URL in getPeople: " + s, e);
                        }
                    })
                    .map(personService::fetchDataByURL)
                    .collect(Collectors.toSet()));
        } catch (InvalidUrlException e) {
            throw new InvalidUrlException("Invalid URL in getPeople: ", e);
        }

        try {
            specie.setFilms(specieDTO.getFilms().stream()
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
            throw new InvalidUrlException("Invalid URL in getFilms: ", e);
        }

        return specie;
    }
}