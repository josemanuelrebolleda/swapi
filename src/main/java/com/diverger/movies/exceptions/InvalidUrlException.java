package com.diverger.movies.exceptions;

public class InvalidUrlException extends RuntimeException {
    public InvalidUrlException(String message, Throwable cause) {
        super(message, cause);
    }
    public InvalidUrlException(String message) {
        super(message);
    }
}