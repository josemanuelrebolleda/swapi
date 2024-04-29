package com.diverger.movies.mapper;

import com.diverger.movies.dto.StarshipDTO;
import com.diverger.movies.mapper.utils.ParseUtils;
import com.diverger.movies.model.Starship;
import com.diverger.movies.service.FilmServiceImpl;
import com.diverger.movies.service.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class StarshipMapper {

    FilmServiceImpl filmService;
    PersonServiceImpl personService;

    Environment env;

    @Lazy   
    @Autowired
    public void setServices(FilmServiceImpl filmService, PersonServiceImpl personService, Environment env) {
        this.filmService = filmService;
        this.personService = personService;
        this.env = env;
    }


    public Starship dtoToStarship(StarshipDTO starshipDTO) {
        Starship starship = new Starship();
        starship.setName(starshipDTO.getName());
        starship.setModel(starshipDTO.getModel());
        starship.setManufacturer(starshipDTO.getManufacturer());
        starship.setCostInCredits(ParseUtils.parseToIntOrDefault(starshipDTO.getCostInCredits()));
        starship.setLength(ParseUtils.parseToDoubleOrDefault(starshipDTO.getLength()));
        starship.setMaxAtmospheringSpeed(ParseUtils.parseToIntOrDefault(starshipDTO.getMaxAtmospheringSpeed()));

        //We split the min and max values of crew
        String[] crewValues = starshipDTO.getCrew().split(env.getProperty("starship.getCrew.splitter"));
        starship.setCrewMin(ParseUtils.parseToIntOrDefault(crewValues[0]));
        starship.setCrewMax(ParseUtils.parseToIntOrDefault(crewValues[1]));
        starship.setConsumables(starshipDTO.getConsumables());
        starship.setHyperdriveRating(ParseUtils.parseToDoubleOrDefault(starshipDTO.getHyperdriveRating()));
        starship.setMGLT(ParseUtils.parseToIntOrDefault(starshipDTO.getMGLT()));
        starship.setStarshipClass(starshipDTO.getStarshipClass());
        starship.setCreated(ParseUtils.parseToDateTime(starshipDTO.getCreated()));
        starship.setEdited(ParseUtils.parseToDateTime(starshipDTO.getEdited()));
        starship.setPilots(new HashSet<>(starshipDTO.getPilots()));
        starship.setFilms(new HashSet<>(starshipDTO.getFilms()));

        starship.setUrl(starshipDTO.getUrl());

        return starship;
    }}