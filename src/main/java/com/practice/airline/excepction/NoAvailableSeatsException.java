package com.practice.airline.excepction;

public class NoAvailableSeatsException extends RuntimeException {
    public NoAvailableSeatsException() {
        super ("Свободных мест больше нет");
    }
}
