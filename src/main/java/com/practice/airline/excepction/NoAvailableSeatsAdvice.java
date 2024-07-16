package com.practice.airline.excepction;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class NoAvailableSeatsAdvice {
    @ResponseBody
    @ExceptionHandler(NoAvailableSeatsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String NoAvailableSeatsExceptionHandler(NoAvailableSeatsException ex) {
        return ex.getMessage();
    }
}
