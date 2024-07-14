package com.practice.airline.DTO;

public class BookingDto {
    private Long humanId;
    private Long flightId;
    private Long seatStatusId;
    private int points;

    public Long getHumanId() {
        return humanId;
    }

    public void setHumanId(Long humanId) {
        this.humanId = humanId;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public Long getSeatStatusId() {
        return seatStatusId;
    }

    public void setSeatStatusId(Long seatStatusId) {
        this.seatStatusId = seatStatusId;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
