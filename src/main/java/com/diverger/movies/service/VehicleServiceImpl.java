package com.diverger.movies.service;

import com.diverger.movies.dto.VehicleDTO;
import com.diverger.movies.mapper.VehicleMapper;
import com.diverger.movies.model.Vehicle;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class VehicleServiceImpl extends SwapiService<Vehicle, VehicleDTO> {

    @Value("${json.name.field}")
    private String jsonNameField;
    @Value("${people.service_url}")
    private String serviceUrl;

    private VehicleMapper vehicleMapper;
    @Lazy
    @Autowired
    public void setMappers(VehicleMapper vehicleMapper) {
        this.vehicleMapper = vehicleMapper;
    }

    @PostConstruct
    public void init() {
        setSERVICE_URL(serviceUrl);
    }

    @Override
    @Cacheable("vehicleByIndex")
    public Vehicle fetchDataByIndex(Integer index) {
        return null;
    }

    @Cacheable("vehicleByURL")
    public Vehicle fetchDataByURL(String url) {
        VehicleDTO response = super.fetchDataByURL(url, VehicleDTO.class);
        return vehicleMapper.dtoToVehicle(response);
    }

    public ResponseEntity<String> formatDataForOutput(Vehicle data) {
        return null;
    }
}