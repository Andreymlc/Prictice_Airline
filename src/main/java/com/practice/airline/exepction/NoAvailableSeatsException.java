package com.practice.airline.exepction;

public class NoAvailableSeatsException extends RuntimeException {
    public NoAvailableSeatsException() {
        super ("Свободных мест ольше нету");
    }
}
