package com.diverger.movies.exceptions;

public class MalformedUrlException extends RuntimeException {

    public MalformedUrlException(String message) {
        super(message);
    }

    public MalformedUrlException(String message, Throwable cause) {
        super(message, cause);
    }
}