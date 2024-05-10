package com.diverger.movies.integration;

import com.diverger.movies.dto.PersonDTO;
import com.diverger.movies.exceptions.ElementNotFoundException;
import com.diverger.movies.mapper.PersonMapper;
import com.diverger.movies.mapper.PersonOutputMapper;
import com.diverger.movies.model.Person;
import com.diverger.movies.model.PersonOutput;
import com.diverger.movies.objects.GetLukeTest;
import com.diverger.movies.service.PersonServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class PersonServiceImplIntegrationTest {

    @Autowired
    private PersonServiceImpl personService;

    @Autowired
    private PersonOutputMapper personOutputMapper;

    @Autowired
    PersonMapper personMapper;


    @Value("${json.name.field}")
    private String jsonNameField;

    @Test
    public void testGetPersonInfoByNameOK() throws JsonProcessingException {
        String name = "Luke Skywalker";
        // Crear el objeto PersonDTO
        PersonDTO personDTO = GetLukeTest.getLukeDTOTest(name);
        Person person = personMapper.dtoToPerson(personDTO);

        // Crear el objeto PersonOutput
        PersonOutput expectedPersonOutput = GetLukeTest.getLukeOutputTest(name);

        ResponseEntity<String> response = personService.getPersonInfoByName(name);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Parse the JSON response to a JsonNode object
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        String personJson = mapper.writeValueAsString(expectedPersonOutput);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<String> responsePersonOutput = new ResponseEntity<>(personJson, headers, HttpStatus.OK);

        assertEquals(responsePersonOutput, response);
    }

    @Test
    public void testGetPersonInfoByNameKO() {
        assertThrows(ElementNotFoundException.class, () -> {
            personService.getPersonInfoByName("Nonexistent Person");
        });
    }

}