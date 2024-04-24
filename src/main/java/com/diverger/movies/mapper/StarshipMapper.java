package com.diverger.movies.mapper;

import com.diverger.movies.dto.StarshipDTO;
import com.diverger.movies.exceptions.InvalidUrlException;
import com.diverger.movies.exceptions.MalformedUrlException;
import com.diverger.movies.model.Starship;
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
import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class StarshipMapper {

    private FilmServiceImpl filmService;
    private PersonServiceImpl personService;

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

    public Starship dtoToStarship(StarshipDTO starshipDTO) {
        Starship starship = new Starship();
        starship.setName(starshipDTO.getName());
        starship.setModel(starshipDTO.getModel());
        starship.setManufacturer(starshipDTO.getManufacturer());
        starship.setCostInCredits(Integer.parseInt(starshipDTO.getCostInCredits()));
        starship.setLength(Double.parseDouble(starshipDTO.getLength()));
        starship.setMaxAtmospheringSpeed(Integer.parseInt(starshipDTO.getMaxAtmospheringSpeed()));

        //We split the min and max values of crew
        String[] crewValues = starshipDTO.getCrew().split("-");
        starship.setCrewMin(Integer.parseInt(crewValues[0]));
        starship.setCrewMax(Integer.parseInt(crewValues[1]));

        starship.setPassengers(Integer.parseInt(starshipDTO.getPassengers()));
        starship.setCargoCapacity(Integer.parseInt(starshipDTO.getCargoCapacity()));
        starship.setConsumables(starshipDTO.getConsumables());
        starship.setHyperdriveRating(Double.parseDouble(starshipDTO.getHyperdriveRating()));
        starship.setMGLT(Integer.parseInt(starshipDTO.getMGLT()));
        starship.setStarshipClass(starshipDTO.getStarshipClass());
        starship.setCreated(LocalDateTime.ofInstant(Instant.parse(starshipDTO.getCreated()), ZoneId.systemDefault()));
        starship.setEdited(LocalDateTime.ofInstant(Instant.parse(starshipDTO.getEdited()), ZoneId.systemDefault()));

        try {
            starship.setUrl(new URL(starshipDTO.getUrl()));
        } catch (MalformedURLException e) {
            throw new MalformedUrlException("Malformed URL in getUrl: " + starshipDTO.getUrl(), e);
        }

        try {
            starship.setPilots(starshipDTO.getPilots().stream()
                    .map(s -> {
                        try {
                            return new URL(s);
                        } catch (MalformedURLException e) {
                            throw new MalformedUrlException("Malformed URL in getPilots: " + s, e);
                        }
                    })
                    .map(personService::fetchDataByURL)
                    .collect(Collectors.toSet()));
        } catch (InvalidUrlException e) {
            throw new InvalidUrlException("Invalid URL in getPilots: ", e);
        }

        try {
            starship.setFilms(starshipDTO.getFilms().stream()
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

        return starship;
    }
}