package com.practice.airline.exсepction;

public class NoAvailableSeatsException extends RuntimeException {
    public NoAvailableSeatsException() {
        super ("Свободных мест ольше нету");
    }
}
