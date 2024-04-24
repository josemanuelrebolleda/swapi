package com.diverger.movies.mapper;

import com.diverger.movies.dto.FilmDTO;
import com.diverger.movies.exceptions.InvalidUrlException;
import com.diverger.movies.model.Film;
import com.diverger.movies.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.stream.Collectors;

@Component
public class FilmMapper {

    private PersonServiceImpl personService;
    private PlanetServiceImpl planetService;
    private StarshipServiceImpl starshipService;
    private VehicleServiceImpl vehicleService;
    private SpecieServiceImpl specieService;

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

    @Lazy   
@Autowired

    public void setStarshipService(StarshipServiceImpl starshipService) {
        this.starshipService = starshipService;
    }

    @Lazy   
@Autowired

    public void setVehicleService(VehicleServiceImpl vehicleService) {
        this.vehicleService = vehicleService;
    }

    @Lazy   
@Autowired

    public void setSpecieService(SpecieServiceImpl specieService) {
        this.specieService = specieService;
    }

    public Film dtoToFilm(FilmDTO filmDTO) {
        Film film = new Film();
        film.setTitle(filmDTO.getTitle());
        film.setEpisode_id(filmDTO.getEpisodeId());
        film.setOpening_crawl(filmDTO.getOpeningCrawl());
        film.setDirector(filmDTO.getDirector());
        film.setProducer(filmDTO.getProducer());
        film.setRelease_date(filmDTO.getReleaseDate());
        film.setCreated(LocalDateTime.ofInstant(Instant.parse(filmDTO.getCreated()), ZoneId.systemDefault()));
        film.setEdited(LocalDateTime.ofInstant(Instant.parse(filmDTO.getEdited()), ZoneId.systemDefault()));

        film.setCharacters(filmDTO.getCharacters().stream()
                .map(url -> {
                    try {
                        return personService.fetchDataByURL(new URL(url));
                    } catch (MalformedURLException e) {
                        throw new InvalidUrlException("Invalid URL for characters: " + url, e);
                    }
                })
                .collect(Collectors.toList()));

        film.setPlanets(filmDTO.getPlanets().stream()
                .map(url -> {
                    try {
                        return planetService.fetchDataByURL(new URL(url));
                    } catch (MalformedURLException e) {
                        throw new InvalidUrlException("Invalid URL for planets: " + url, e);
                    }
                })
                .collect(Collectors.toList()));

        film.setStarships(filmDTO.getStarships().stream()
                .map(url -> {
                    try {
                        return starshipService.fetchDataByURL(new URL(url));
                    } catch (MalformedURLException e) {
                        throw new InvalidUrlException("Invalid URL for starships: " + url, e);
                    }
                })
                .collect(Collectors.toList()));

        film.setVehicles(filmDTO.getVehicles().stream()
                .map(url -> {
                    try {
                        return vehicleService.fetchDataByURL(new URL(url));
                    } catch (MalformedURLException e) {
                        throw new InvalidUrlException("Invalid URL for vehicles: " + url, e);
                    }
                })
                .collect(Collectors.toList()));

        film.setSpecies(filmDTO.getSpecies().stream()
                .map(url -> {
                    try {
                        return specieService.fetchDataByURL(new URL(url));
                    } catch (MalformedURLException e) {
                        throw new InvalidUrlException("Invalid URL for species: " + url, e);
                    }
                })
                .collect(Collectors.toList()));

        try {
            film.setUrl(new URL(filmDTO.getUrl()));
        } catch (MalformedURLException e) {
            throw new InvalidUrlException("Invalid URL: " + filmDTO.getUrl(), e);
        }
        return film;
    }
}