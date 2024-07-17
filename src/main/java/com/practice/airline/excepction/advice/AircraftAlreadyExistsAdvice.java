package com.practice.airline.excepction.advice;

import com.practice.airline.excepction.AircraftAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AircraftAlreadyExistsAdvice {
    @ResponseBody
    @ExceptionHandler(AircraftAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String aircraftAlreadyExistsException(AircraftAlreadyExistsException ex) {
        return ex.getMessage();
    }
}
