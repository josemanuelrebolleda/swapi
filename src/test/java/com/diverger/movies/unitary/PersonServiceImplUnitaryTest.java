package com.diverger.movies.unitary;

import com.diverger.movies.dto.PersonDTO;
import com.diverger.movies.mapper.PersonMapper;
import com.diverger.movies.mapper.PersonOutputMapper;
import com.diverger.movies.model.FilmOutput;
import com.diverger.movies.model.Person;
import com.diverger.movies.model.PersonOutput;
import com.diverger.movies.objects.GetLukeTest;
import com.diverger.movies.service.PersonServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PersonServiceImplUnitaryTest {

    @Autowired
    PersonMapper personMapper;

    @Autowired
    PersonOutputMapper personOutputMapper;

    @Autowired
    Environment env;

    @Mock
    RestTemplate restTemplateMock;

    @InjectMocks
    PersonServiceImpl personServiceMock;




    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        personServiceMock.setBASE_URL(env.getProperty("swapi.base_url"));
        personServiceMock.setSERVICE_URL(env.getProperty("people.service_url"));
        personServiceMock.setSLASH(env.getProperty("slash"));
        personServiceMock.setRestTemplate(restTemplateMock);
        personServiceMock.setMappers(personMapper, personOutputMapper, env);
    }

    @Test
    public void testGetPersonInfoByName() throws Exception {
        String name = "Luke Skywalker_TEST";
        // Crear el objeto PersonDTO
        PersonDTO personDTO = GetLukeTest.getLukeDTOTest(name);
        Person person = personMapper.dtoToPerson(personDTO);

        // Crear el objeto PersonOutput
        PersonOutput expectedPersonOutput = GetLukeTest.getLukeOutputTest(name);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("count", 1);

        when(restTemplateMock.getForObject(anyString(), eq(Map.class))).thenReturn(responseMap);
        when(restTemplateMock.getForObject(anyString(), eq(PersonDTO.class))).thenReturn(personDTO);
//        when(personMapper.dtoToPerson(any(PersonDTO.class))).thenCallRealMethod();
        // Ejecutar
        ResponseEntity<String> response = personServiceMock.getPersonInfoByName(name);

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        String personJson = mapper.writeValueAsString(personOutputMapper.mapToPersonOutput(person));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<String> responsePersonOutput = new ResponseEntity<>(personJson, headers, HttpStatus.OK);

        assertEquals(responsePersonOutput, response);
    }
}