package com.diverger.movies.mapper;

import com.diverger.movies.dto.PlanetDTO;
import com.diverger.movies.exceptions.InvalidUrlException;
import com.diverger.movies.model.Film;
import com.diverger.movies.model.Person;
import com.diverger.movies.model.Planet;
import com.diverger.movies.service.FilmServiceImpl;
import com.diverger.movies.service.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PlanetMapper {

    @Lazy   
@Autowired

    private PersonServiceImpl personService;

    @Lazy   
@Autowired

    private FilmServiceImpl filmService;

    public Planet dtoToPlanet(PlanetDTO planetDTO) {
        Planet planet = new Planet();
        planet.setName(planetDTO.getName());
        planet.setRotationPeriod(Integer.parseInt(planetDTO.getRotationPeriod()));
        planet.setOrbitalPeriod(Integer.parseInt(planetDTO.getOrbitalPeriod()));
        planet.setDiameter(Integer.parseInt(planetDTO.getDiameter()));
        planet.setClimate(planetDTO.getClimate());
        planet.setGravity(planetDTO.getGravity());
        planet.setTerrain(planetDTO.getTerrain());
        planet.setSurfaceWater(Integer.parseInt(planetDTO.getSurfaceWater()));
        planet.setPopulation(Integer.parseInt(planetDTO.getPopulation()));
        planet.setCreated(LocalDateTime.ofInstant(Instant.parse(planetDTO.getCreated()), ZoneId.systemDefault()));
        planet.setEdited(LocalDateTime.ofInstant(Instant.parse(planetDTO.getEdited()), ZoneId.systemDefault()));

        planet.setResidents(planetDTO.getResidents().stream()
                .map(url -> {
                    try {
                        return personService.fetchDataByURL(new URL(url));
                    } catch (MalformedURLException e) {
                        throw new InvalidUrlException("Invalid URL for residents: " + url, e);
                    }
                })
                .collect(Collectors.toSet()));

        planet.setFilms(planetDTO.getFilms().stream()
                .map(url -> {
                    try {
                        return filmService.fetchDataByURL(new URL(url));
                    } catch (MalformedURLException e) {
                        throw new InvalidUrlException("Invalid URL for films: " + url, e);
                    }
                })
                .collect(Collectors.toSet()));

        try {
            planet.setUrl(new URL(planetDTO.getUrl()));
        } catch (MalformedURLException e) {
            throw new InvalidUrlException("Invalid URL: " + planetDTO.getUrl(), e);
        }
        return planet;
    }
}