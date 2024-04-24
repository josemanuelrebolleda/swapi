package com.diverger.movies.service;

import com.diverger.movies.dto.StarshipDTO;
import com.diverger.movies.dto.VehicleDTO;
import com.diverger.movies.exceptions.InvalidUrlException;
import com.diverger.movies.mapper.VehicleMapper;
import com.diverger.movies.model.Vehicle;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URL;

@Service
public class VehicleServiceImpl extends SwapiService<Vehicle> {
    @Lazy   
@Autowired

    private VehicleMapper vehicleMapper;

    @Value("${json.name.field}")
    private String jsonNameField;
    @Value("${people.service_url}")
    private String serviceUrl;

    @PostConstruct
    public void init() {
        setSERVICE_URL(serviceUrl);
    }

    @Override
    @Cacheable("vehicleByName")
    public Vehicle fetchDataByName(String name) {
        return null;
    }

    @Override
    @Cacheable("vehicleByIndex")
    public Vehicle fetchDataByIndex(int index) {
        return null;
    }

    @Cacheable("vehicleByURL")
    public Vehicle fetchDataByURL(URL url) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            VehicleDTO response = restTemplate.getForObject(url.toString(), VehicleDTO.class);
            if (response == null) {
                throw new InvalidUrlException("Planet: Response is null for URL: " + url);
            }
            return vehicleMapper.dtoToVehicle(response);
        } catch (Exception e) {
            throw new InvalidUrlException("Planet: Failed to fetch data from URL: " + url, e);
        }
    }

    public ResponseEntity<String> formatDataForOutput(Vehicle data) {
        return null;
    }
}