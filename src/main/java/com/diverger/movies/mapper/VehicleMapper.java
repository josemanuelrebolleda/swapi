package com.diverger.movies.mapper;

import com.diverger.movies.dto.VehicleDTO;
import com.diverger.movies.mapper.utils.ParseUtils;
import com.diverger.movies.model.Vehicle;
import com.diverger.movies.service.FilmServiceImpl;
import com.diverger.movies.service.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class VehicleMapper {

    FilmServiceImpl filmService;
    PersonServiceImpl personService;

    @Lazy
    @Autowired
    public void setServices(FilmServiceImpl filmService, PersonServiceImpl personService) {
        this.filmService = filmService;
        this.personService = personService;
    }

    public Vehicle dtoToVehicle(VehicleDTO vehicleDTO) {
        Vehicle vehicle = new Vehicle();
        vehicle.setName(vehicleDTO.getName());
        vehicle.setModel(vehicleDTO.getModel());
        vehicle.setManufacturer(vehicleDTO.getManufacturer());
        vehicle.setCostInCredits(ParseUtils.parseToIntOrDefault(vehicleDTO.getCostInCredits()));
        vehicle.setLength(ParseUtils.parseToDoubleOrDefault(vehicleDTO.getLength()));
        vehicle.setMaxAtmospheringSpeed(ParseUtils.parseToIntOrDefault(vehicleDTO.getMaxAtmospheringSpeed()));
        vehicle.setCrew(ParseUtils.parseToIntOrDefault(vehicleDTO.getCrew()));
        vehicle.setPassengers(ParseUtils.parseToIntOrDefault(vehicleDTO.getPassengers()));
        vehicle.setCargoCapacity(ParseUtils.parseToIntOrDefault(vehicleDTO.getCargoCapacity()));
        vehicle.setConsumables(vehicleDTO.getConsumables());
        vehicle.setVehicleClass(vehicleDTO.getVehicleClass());
        vehicle.setCreated(ParseUtils.parseToDateTime(vehicleDTO.getCreated()));
        vehicle.setEdited(ParseUtils.parseToDateTime(vehicleDTO.getEdited()));

        vehicle.setUrl(vehicleDTO.getUrl());

        vehicle.setPilots(new HashSet<>(vehicleDTO.getPilots()));
        vehicle.setFilms(new HashSet<>(vehicleDTO.getFilms()));

        return vehicle;
    }
}