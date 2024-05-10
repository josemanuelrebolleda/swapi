package com.diverger.movies.objects;

import com.diverger.movies.dto.PersonDTO;
import com.diverger.movies.model.FilmOutput;
import com.diverger.movies.model.PersonOutput;

import java.util.ArrayList;
import java.util.Arrays;

public class GetLukeTest {
    public static PersonDTO getLukeDTOTest(String name) {
        // Crear el objeto PersonDTO
        PersonDTO personDTO = new PersonDTO();
        personDTO.setName(name);
        personDTO.setHeight("172");
        personDTO.setMass("77");
        personDTO.setHairColor("blond");
        personDTO.setSkinColor("fair");
        personDTO.setEyeColor("blue");
        personDTO.setBirthYear("19BBY");
        personDTO.setGender("male");
        personDTO.setHomeworld("https://swapi.trileuco.com/api/planets/1/");
        personDTO.setFilms(Arrays.asList(
                "https://swapi.trileuco.com/api/films/1/",
                "https://swapi.trileuco.com/api/films/2/",
                "https://swapi.trileuco.com/api/films/3/",
                "https://swapi.trileuco.com/api/films/6/"
        ));
        personDTO.setVehicles(Arrays.asList(
                "https://swapi.trileuco.com/api/vehicles/14/",
                "https://swapi.trileuco.com/api/vehicles/30/"
        ));
        personDTO.setStarships(Arrays.asList(
                "https://swapi.trileuco.com/api/starships/12/",
                "https://swapi.trileuco.com/api/starships/22/"
        ));
        personDTO.setSpecies(new ArrayList<>());
        personDTO.setCreated("2014-12-09T13:50:51.644000Z");
        personDTO.setEdited("2014-12-20T21:17:56.891000Z");
        personDTO.setUrl("https://swapi.trileuco.com/api/people/1/");

        return personDTO;
    }
    public static PersonOutput getLukeOutputTest(String name) {
        return new PersonOutput(
                name,
                "19BBY",
                "male",
                "Tatooine",
                "Snowspeeder",
                Arrays.asList(
                        new FilmOutput("Revenge of the Sith", "2005-05-19"),
                        new FilmOutput("A New Hope", "1977-05-25"),
                        new FilmOutput("The Empire Strikes Back", "1980-05-17"),
                        new FilmOutput("Return of the Jedi", "1983-05-25")
                )
        );

    }


    }
