package com.diverger.movies.exceptions;

public class NullParameterException extends RuntimeException {
    public NullParameterException(String message) {
        super(message);
    }
}