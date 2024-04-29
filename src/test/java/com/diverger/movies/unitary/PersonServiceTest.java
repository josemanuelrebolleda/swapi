package com.diverger.movies.unitary;

import com.diverger.movies.mapper.PersonOutputMapper;
import com.diverger.movies.model.Person;
import com.diverger.movies.service.PersonServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@SpringBootTest
public class PersonServiceTest {

    @Autowired
    private PersonServiceImpl personService;

    @Autowired
    PersonOutputMapper personOutputMapper;
    @Value("${swapi.base_url}")
    String BASE_URL;
    @Value("${people.service_url}")
    String SERVICE_URL;

    @Value("${json.count.field}")
    String COUNT = "count";
    RestTemplate restTemplate = new RestTemplate();


    @Test
    public void testFetchDataByIndexAndGetPersonInfoByName() {
        RestTemplate restTemplate = new RestTemplate();
        String url = BASE_URL + SERVICE_URL;
        Map response = restTemplate.getForObject(url, Map.class);
        if (response != null && response.containsKey(COUNT)) {
            int count = (Integer) response.get(COUNT);

            int index = 1;
            int indexUrl = 1;
            while (index <= count) {
                Person person = personService.fetchDataByIndex(indexUrl);
                if (person != null) {
                    personService.getPersonInfoByName(person.getName());
                    System.out.println("Index: " + index + " IndexURL: " + indexUrl + " " + personOutputMapper.mapToPersonOutput(person));
                    index++;
                }
                indexUrl++;
            }
        }
    }
}