package com.diverger.movies.mapper;

import com.diverger.movies.dto.PlanetDTO;
import com.diverger.movies.mapper.utils.ParseUtils;
import com.diverger.movies.model.Planet;
import com.diverger.movies.service.FilmServiceImpl;
import com.diverger.movies.service.PersonServiceImpl;
import com.diverger.movies.service.PlanetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class PlanetMapper {

    PersonServiceImpl personService;
    FilmServiceImpl filmService;

    @Lazy
    @Autowired
    public void setServices(FilmServiceImpl filmService, PersonServiceImpl personService, PlanetServiceImpl planetService) {
        this.filmService = filmService;
        this.personService = personService;
    }

    public Planet dtoToPlanet(PlanetDTO planetDTO) {
        Planet planet = new Planet();
        planet.setName(planetDTO.getName());
        planet.setRotationPeriod(ParseUtils.parseToIntOrDefault(planetDTO.getRotationPeriod()));
        planet.setOrbitalPeriod(ParseUtils.parseToIntOrDefault(planetDTO.getOrbitalPeriod()));
        planet.setDiameter(ParseUtils.parseToIntOrDefault(planetDTO.getDiameter()));
        planet.setClimate(planetDTO.getClimate());
        planet.setGravity(planetDTO.getGravity());
        planet.setTerrain(planetDTO.getTerrain());
        planet.setSurfaceWater(ParseUtils.parseToDoubleOrDefault(planetDTO.getSurfaceWater()));
        planet.setPopulation(ParseUtils.parseToLongOrDefault(planetDTO.getPopulation()));
        planet.setCreated(ParseUtils.parseToDateTime(planetDTO.getCreated()));
        planet.setEdited(ParseUtils.parseToDateTime(planetDTO.getEdited()));

        planet.setResidents(new HashSet<>(planetDTO.getResidents()));
        planet.setFilms(new HashSet<>(planetDTO.getFilms()));

        planet.setUrl(planetDTO.getUrl());

        return planet;
    }}