package com.practice.airline.excepction;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class EntityAdvice {
    @ResponseBody
    @ExceptionHandler(AircraftAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String aircraftAlreadyExistsException(AircraftAlreadyExistsException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String entityNotFoundHandler(EntityNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(HumanAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String humanAlreadyExistsHandler(HumanAlreadyExistsException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(InvalidFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String invalidFormatException(InvalidFormatException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(NoAvailableSeatsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String noAvailableSeatsExceptionHandler(NoAvailableSeatsException ex) {
        return ex.getMessage();
    }
}
