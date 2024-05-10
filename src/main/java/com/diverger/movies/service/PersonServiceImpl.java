package com.diverger.movies.service;

import com.diverger.movies.dto.PersonDTO;
import com.diverger.movies.exceptions.ElementNotFoundException;
import com.diverger.movies.exceptions.JsonParsingException;
import com.diverger.movies.mapper.PersonMapper;
import com.diverger.movies.mapper.PersonOutputMapper;
import com.diverger.movies.model.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.Map;

@Service
public class PersonServiceImpl extends SwapiService<Person, PersonDTO> {

    @Value("${json.name.field}")
    private String JSON_NAME_FIELD;

    PersonMapper personMapper;
    PersonOutputMapper personOutputMapper;

    Environment env;
    @Lazy
    @Autowired
    public void setMappers(PersonMapper personMapper, PersonOutputMapper personOutputMapper, Environment env) {
        this.personMapper = personMapper;
        this.personOutputMapper = personOutputMapper;
        this.env = env;
    }

    @Value("${people.service_url}")
    private String serviceUrl;

    @PostConstruct
    public void init() {
        setSERVICE_URL(serviceUrl);
    }


    public ResponseEntity<String> getPersonInfoByName(String name) {
        Person person = fetchDataByName(name);
        if (person != null) {
            return formatDataForOutput(person);
        } else {
            throw new ElementNotFoundException ("Person not found: " + name);
        }
    }


    @Cacheable("personByName")
    public Person fetchDataByName(String name) {
        Person person = null;
        String url = BASE_URL + SERVICE_URL;
        Map response = restTemplate.getForObject(url, Map.class);
        boolean found = false;
        if (response != null && response.containsKey(COUNT)) {
            int count = (Integer) response.get(COUNT);
            int index = 1;
            int indexUrl = 1;

            while (!found && index <= count) {
                person = fetchDataByIndex(indexUrl);
                if (person == null) {
                    while (person == null) {
                        indexUrl++;
                        person = fetchDataByIndex(indexUrl);
                    }
                }
                if (person.getName().equalsIgnoreCase(name)) {
                    found = true;
                } else {
                    index++;
                    indexUrl++;
               }
            }

            if (!found) {
                throw new ElementNotFoundException("Person with name " + name + " not found");
            }
        }

        return person;
    }

    @Override
    @Cacheable("personByIndex")
    public Person fetchDataByIndex(Integer index) {
        String url = BASE_URL + SERVICE_URL + index + SLASH;
        PersonDTO response = null;
        try {
            response = restTemplate.getForObject(url, PersonDTO.class);
        } catch (RestClientException e) {
            return null;
        }
        if (response == null) {
            return null;
        }
        return personMapper.dtoToPerson(response);
    }


    @Cacheable("personByURL")
    public Person fetchDataByURL(String url) {
        PersonDTO response = super.fetchDataByURL(url, PersonDTO.class);
        return personMapper.dtoToPerson(response);
    }

    public ResponseEntity<String> formatDataForOutput(Person data) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            String personJson = mapper.writeValueAsString(personOutputMapper.mapToPersonOutput(data));
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return new ResponseEntity<>(personJson, headers, HttpStatus.OK);
        } catch (Exception e) {
            throw new JsonParsingException("Error converting Person to PersonOutput", e);
        }
    }
}