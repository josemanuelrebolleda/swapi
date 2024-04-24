package com.diverger.movies.mapper.utils;

import com.diverger.movies.exceptions.InvalidUrlException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MapperUtils {

    public static int stringToInt(String s) {
        return s != null ? Integer.parseInt(s) : 0;
    }

    public static String intToString(int i) {
        return Integer.toString(i);
    }

    public static List<String> setToList(Set<?> set) {
        return set.stream().map(Object::toString).collect(Collectors.toList());
    }

    public static Set<String> listToSet(List<String> list) {
        return new HashSet<>(list);
    }

    public static String urlToString(URL url) {
        return url.toString();
    }

    public static URL stringToUrl(String s) {
        try {
            return new URL(s);
        } catch (MalformedURLException e) {
            throw new InvalidUrlException("Invalid URL: " + s, e);
        }
    }
}
