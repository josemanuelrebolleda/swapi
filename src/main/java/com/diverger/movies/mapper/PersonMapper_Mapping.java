package com.diverger.movies.mapper;

import com.diverger.movies.dto.PersonDTO;
import com.diverger.movies.exceptions.InvalidUrlException;
import com.diverger.movies.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface PersonMapper_Mapping {
    @Mapping(target = "height", source = "height", qualifiedByName = "stringToInt")
    @Mapping(target = "mass", source = "mass", qualifiedByName = "stringToInt")
    @Mapping(target = "films", source = "films", qualifiedByName = "listStringToSetOfUrls")
    @Mapping(target = "species", source = "species", qualifiedByName = "listStringToSetOfUrls")
    @Mapping(target = "vehicles", source = "vehicles", qualifiedByName = "listStringToSetOfUrls")
    @Mapping(target = "starships", source = "starships", qualifiedByName = "listStringToSetOfUrls")
    @Mapping(target = "created", source = "created", dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    @Mapping(target = "edited", source = "edited", dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    @Mapping(target = "url", source = "url", qualifiedByName = "urlToString")
    Person dtoToPerson(PersonDTO personDTO);
// JMRD - ¿Necesito este método?
//    @Mapping(target = "height", source = "height", qualifiedByName = "intToString")
//    @Mapping(target = "mass", source = "mass", qualifiedByName = "intToString")
//    @Mapping(target = "films", source = "films", qualifiedByName = "setURLToListString")
//    @Mapping(target = "species", source = "species", qualifiedByName = "setURLToListString")
//    @Mapping(target = "vehicles", source = "vehicles", qualifiedByName = "setURLToListString")
//    @Mapping(target = "starships", source = "starships", qualifiedByName = "setURLToListString")
//    @Mapping(target = "created", source = "created", dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
//    @Mapping(target = "edited", source = "edited", dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
//    @Mapping(target = "url", source = "url", qualifiedByName = "stringToUrl")
//    PersonDTO personToDto(Person person);

    default int stringToInt(String s) {
        return s != null ? Integer.parseInt(s) : 0;
    }

    default String intToString(int i) {
        return Integer.toString(i);
    }

    default List<String> setURLToListString(Set<URL> set) {
        return set.stream().map(URL::toString).collect(Collectors.toList());
    }

    default Set<URL> listStringToSetOfUrls(List<String> list) {
        return list.stream().map(this::stringToUrl).collect(Collectors.toSet());
    }
    default String urlToString(URL url) {
        return url.toString();
    }

    default URL stringToUrl(String s) {
        try {
            return new URL(s);
        } catch (MalformedURLException e) {
            throw new InvalidUrlException("Invalid URL: " + s, e);
        }
    }
}