package com.diverger.movies.mapper;

import com.diverger.movies.dto.SpecieDTO;
import com.diverger.movies.mapper.utils.ParseUtils;
import com.diverger.movies.model.Specie;
import com.diverger.movies.service.FilmServiceImpl;
import com.diverger.movies.service.PersonServiceImpl;
import com.diverger.movies.service.PlanetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;

@Component
public class SpecieMapper {

    Environment env;

    FilmServiceImpl filmService;
    PersonServiceImpl personService;
    PlanetServiceImpl planetService;

    @Lazy   
    @Autowired
    public void setServices(FilmServiceImpl filmService, PersonServiceImpl personService, PlanetServiceImpl planetService, Environment env) {
        this.filmService = filmService;
        this.personService = personService;
        this.planetService = planetService;
        this.env = env;
    }


    public Specie dtoToSpecie(SpecieDTO specieDTO) {
        Specie specie = new Specie();
        specie.setName(specieDTO.getName());
        specie.setClassification(specieDTO.getClassification());
        specie.setDesignation(specieDTO.getDesignation());
        specie.setAverageHeight(ParseUtils.parseToIntOrDefault(specieDTO.getAverageHeight()));
        specie.setAverageLifespan(ParseUtils.parseToIntOrDefault(specieDTO.getAverageLifespan()));
        specie.setLanguage(specieDTO.getLanguage());
        specie.setSkinColors(new HashSet<>(Arrays.asList(specieDTO.getSkinColors().split(env.getProperty("specie.getSkinColors.splitter")))));
        specie.setHairColors(new HashSet<>(Arrays.asList(specieDTO.getHairColors().split(env.getProperty("specie.getSkinColors.splitter")))));
        specie.setEyeColors(new HashSet<>(Arrays.asList(specieDTO.getEyeColors().split(env.getProperty("specie.getSkinColors.splitter")))));
        specie.setCreated(ParseUtils.parseToDateTime(specieDTO.getCreated()));
        specie.setEdited(ParseUtils.parseToDateTime(specieDTO.getEdited()));

        specie.setPeople(new HashSet<>(specieDTO.getPeople()));
        specie.setFilms(new HashSet<>(specieDTO.getFilms()));

        specie.setUrl(specieDTO.getUrl());

        return specie;
    }
}