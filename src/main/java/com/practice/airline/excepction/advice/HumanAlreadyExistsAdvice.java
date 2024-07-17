package com.practice.airline.excepction.advice;

import com.practice.airline.excepction.HumanAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class HumanAlreadyExistsAdvice {
    @ResponseBody
    @ExceptionHandler(HumanAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String humanAlreadyExistsHandler(HumanAlreadyExistsException ex) {
        return ex.getMessage();
    }
}
