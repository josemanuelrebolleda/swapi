package com.diverger.movies.mapper;

import com.diverger.movies.dto.VehicleDTO;
import com.diverger.movies.exceptions.InvalidUrlException;
import com.diverger.movies.exceptions.MalformedUrlException;
import com.diverger.movies.model.Vehicle;
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
import java.util.stream.Collectors;

@Component
public class VehicleMapper {

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

    public Vehicle dtoToVehicle(VehicleDTO vehicleDTO) {
        Vehicle vehicle = new Vehicle();
        vehicle.setName(vehicleDTO.getName());
        vehicle.setModel(vehicleDTO.getModel());
        vehicle.setManufacturer(vehicleDTO.getManufacturer());
        vehicle.setCostInCredits(Integer.parseInt(vehicleDTO.getCostInCredits()));
        vehicle.setLength(Double.parseDouble(vehicleDTO.getLength()));
        vehicle.setMaxAtmospheringSpeed(Integer.parseInt(vehicleDTO.getMaxAtmospheringSpeed()));
        vehicle.setCrew(Integer.parseInt(vehicleDTO.getCrew()));
        vehicle.setPassengers(Integer.parseInt(vehicleDTO.getPassengers()));
        vehicle.setCargoCapacity(Integer.parseInt(vehicleDTO.getCargoCapacity()));
        vehicle.setConsumables(vehicleDTO.getConsumables());
        vehicle.setVehicleClass(vehicleDTO.getVehicleClass());
        vehicle.setCreated(LocalDateTime.ofInstant(Instant.parse(vehicleDTO.getCreated()), ZoneId.systemDefault()));
        vehicle.setEdited(LocalDateTime.ofInstant(Instant.parse(vehicleDTO.getEdited()), ZoneId.systemDefault()));

        try {
            vehicle.setUrl(new URL(vehicleDTO.getUrl()));
        } catch (MalformedURLException e) {
            throw new MalformedUrlException("Malformed URL in getUrl: " + vehicleDTO.getUrl(), e);
        }

        try {
            vehicle.setPilots(vehicleDTO.getPilots().stream()
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
            vehicle.setFilms(vehicleDTO.getFilms().stream()
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

        return vehicle;
    }
}