package com.practice.airline.excepction;


public class HumanAlreadyExistsException extends RuntimeException {
    public HumanAlreadyExistsException(String message) {
        super(message);
    }
}
