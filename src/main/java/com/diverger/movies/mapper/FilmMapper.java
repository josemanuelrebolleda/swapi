package com.diverger.movies.mapper;

import com.diverger.movies.dto.FilmDTO;
import com.diverger.movies.mapper.utils.ParseUtils;
import com.diverger.movies.model.Film;
import com.diverger.movies.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.HashSet;

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
        film.setEpisodeId(filmDTO.getEpisodeId());
        film.setOpeningCrawl(filmDTO.getOpeningCrawl());
        film.setDirector(filmDTO.getDirector());
        film.setProducer(filmDTO.getProducer());
        film.setReleaseDate(filmDTO.getReleaseDate());
        film.setCreated(ParseUtils.parseToDateTime(filmDTO.getCreated()));
        film.setEdited(ParseUtils.parseToDateTime(filmDTO.getEdited()));

        film.setCharacters(new HashSet<>(filmDTO.getCharacters()));
        film.setPlanets(new HashSet<>(filmDTO.getPlanets()));
        film.setStarships(new HashSet<>(filmDTO.getStarships()));
        film.setVehicles(new HashSet<>(filmDTO.getVehicles()));
        film.setSpecies(new HashSet<>(filmDTO.getSpecies()));

        film.setUrl(filmDTO.getUrl());

        return film;
    }
}