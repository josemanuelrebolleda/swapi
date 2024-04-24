package com.diverger.movies.service;

import com.diverger.movies.dto.FilmDTO;
import com.diverger.movies.dto.PersonDTO;
import com.diverger.movies.exceptions.InvalidUrlException;
import com.diverger.movies.exceptions.JsonParsingException;
import com.diverger.movies.exceptions.PersonNotFoundException;
import com.diverger.movies.mapper.PersonMapper;
import com.diverger.movies.mapper.PersonOutputMapper;
import com.diverger.movies.model.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class PersonServiceImpl extends SwapiService<Person> {

    @Value("${json.name.field}")
    private String jsonNameField;

    private PersonMapper personMapper;
    private PersonOutputMapper personOutputMapper;

    @Lazy
    @Autowired
    public void setPersonMapper(PersonMapper personMapper) {
        this.personMapper = personMapper;
    }

    @Lazy
    @Autowired
    public void setPersonOutputMapper(PersonOutputMapper personOutputMapper) {
        this.personOutputMapper = personOutputMapper;
    }

    @Value("${people.service_url}")
    private String serviceUrl;

    @PostConstruct
    public void init() {
        setSERVICE_URL(serviceUrl);
    }

    @Override
    @Cacheable("personByName")
    public Person fetchDataByName(String name) {
        int index = 1;
        boolean found = false;
        PersonDTO personDTO = null;
        while (!found) {
            String url = BASE_URL + SERVICE_URL + index + "/";
            personDTO = restTemplate.getForObject(url, PersonDTO.class);
            if (personDTO == null) {
                throw new PersonNotFoundException("Person " + name + " not found");
            }
            if (personDTO.getName().equalsIgnoreCase(name)) {
                found = true;
            } else {
                index++;
            }
        }
        return personMapper.dtoToPerson(personDTO);
    }

    @Override
    @Cacheable("personByIndex")
    public Person fetchDataByIndex(int index) {
        String url = BASE_URL + SERVICE_URL + index + "/";
        PersonDTO response = restTemplate.getForObject(url, PersonDTO.class);
        if (response == null) {
            throw new PersonNotFoundException("Person with index " + index + " not found");
        }
        return personMapper.dtoToPerson(response);
    }

    @Cacheable("personByURL")
    public Person fetchDataByURL(URL url) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            PersonDTO response = restTemplate.getForObject(url.toString(), PersonDTO.class);
            if (response == null) {
                throw new InvalidUrlException("Person: Response is null for URL: " + url);
            }
            return personMapper.dtoToPerson(response);
        } catch (Exception e) {
            throw new InvalidUrlException("Person: Failed to fetch data from URL: " + url, e);
        }
    }

    public ResponseEntity<String> formatDataForOutput(Person data) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String personJson = mapper.writeValueAsString(personOutputMapper.mapToPersonOutput(data));
            return ResponseEntity.ok(personJson);
        } catch (Exception e) {
            throw new JsonParsingException("Error converting Person to PersonOutput", e);
        }
    }
}