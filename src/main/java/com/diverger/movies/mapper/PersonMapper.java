package com.diverger.movies.mapper;

import com.diverger.movies.dto.PersonDTO;
import com.diverger.movies.mapper.utils.ParseUtils;
import com.diverger.movies.model.Person;
import com.diverger.movies.service.FilmServiceImpl;
import com.diverger.movies.service.SpecieServiceImpl;
import com.diverger.movies.service.StarshipServiceImpl;
import com.diverger.movies.service.VehicleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class PersonMapper {

    FilmServiceImpl filmService;
    SpecieServiceImpl speciesService;
    VehicleServiceImpl vehiclesService;
    StarshipServiceImpl starshipsService;


    @Lazy
    @Autowired
    public void setServices(FilmServiceImpl filmService, SpecieServiceImpl speciesService, VehicleServiceImpl vehiclesService, StarshipServiceImpl starshipsService) {
        this.filmService = filmService;
        this.speciesService = speciesService;
        this.vehiclesService = vehiclesService;
        this.starshipsService = starshipsService;
    }

    public Person dtoToPerson(PersonDTO personDTO) {
        Person person = new Person();
        person.setName(personDTO.getName());
        person.setHeight(ParseUtils.parseToIntOrDefault(personDTO.getHeight()));
        person.setMass(ParseUtils.parseToDoubleOrDefault(personDTO.getMass()));
        person.setHair_color(personDTO.getHairColor());
        person.setSkin_color(personDTO.getSkinColor());
        person.setEye_color(personDTO.getEyeColor());
        person.setBirth_year(personDTO.getBirthYear());
        person.setGender(personDTO.getGender());
        person.setHomeworld(personDTO.getHomeworld());
        person.setCreated(ParseUtils.parseToDateTime(personDTO.getCreated()));
        person.setEdited(ParseUtils.parseToDateTime(personDTO.getEdited()));

        person.setFilms(new HashSet<>(personDTO.getFilms()));
        person.setSpecies(new HashSet<>(personDTO.getSpecies()));
        person.setVehicles(new HashSet<>(personDTO.getVehicles()));
        person.setStarships(new HashSet<>(personDTO.getStarships()));

        person.setUrl(personDTO.getUrl());

        return person;
    }
}