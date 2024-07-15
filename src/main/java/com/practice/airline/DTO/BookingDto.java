package com.practice.airline.DTO;

import java.time.LocalDateTime;

public class BookingDto {
    private LocalDateTime date;
    private String fullName;
    private String passportNumber;
    private String departureIata;
    private String arrivalIata;
    private String seatStatus;
    private int price;

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getDepartureIata() {
        return departureIata;
    }

    public void setDepartureIata(String departureIata) {
        this.departureIata = departureIata;
    }

    public String getArrivalIata() {
        return arrivalIata;
    }

    public void setArrivalIata(String arrivalIata) {
        this.arrivalIata = arrivalIata;
    }

    public String getSeatStatus() {
        return seatStatus;
    }

    public void setSeatStatus(String seatStatus) {
        this.seatStatus = seatStatus;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
