package com.practice.airline.excepction;

public class AircraftAlreadyExistsException extends RuntimeException {
    public AircraftAlreadyExistsException(String message) {
        super(message);
    }
}
