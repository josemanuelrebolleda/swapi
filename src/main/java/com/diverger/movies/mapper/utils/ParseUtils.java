package com.diverger.movies.mapper.utils;

import org.apache.commons.lang3.math.NumberUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;

public class ParseUtils {

    public static LocalDateTime parseToDateTime(String input) {
        try {
            return LocalDateTime.ofInstant(Instant.parse(input), ZoneId.systemDefault());
        } catch (DateTimeParseException e) {
            return LocalDateTime.MAX;
        }
    }

    public static Integer parseToIntOrDefault(String input) {
        return NumberUtils.isParsable(input) ? Integer.parseInt(input) : Integer.MAX_VALUE;
    }

    public static Long parseToLongOrDefault(String input) {
        return NumberUtils.isParsable(input) ? Long.parseLong(input) : Long.MAX_VALUE;
    }

    public static Double parseToDoubleOrDefault(String input) {
        return NumberUtils.isParsable(input) ? Double.parseDouble(input) : Double.MAX_VALUE;
    }

}