package com.practice.airline.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "booking")
public class Booking extends BaseEntity {
    private Human human;
    private Flight flight;
    private LocalDateTime date;
    private SeatStatus seatStatus;
    private int price;
    private int spentPoints;

    public Booking(Human human, Flight flight, LocalDateTime date, SeatStatus seatStatus, int price, int spentPoints) {
        setHuman(human);
        setFlight(flight);
        setDate(date);
        setSeatStatus(seatStatus);
        setPrice(price);
        setSpentPoints(spentPoints);
    }

    protected Booking() {}

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "human_id", nullable = false)
    public Human getHuman() {
        return human;
    }

    public void setHuman(Human human) {
        this.human = human;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "flight_id", nullable = false)
    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    @Column(name = "date", nullable = false)
    public LocalDateTime  getDate() {
        return date;
    }

    public void setDate(LocalDateTime  date) {
        this.date = date;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "seat_status_id", nullable = false)
    public SeatStatus getSeatStatus() {
        return seatStatus;
    }

    public void setSeatStatus(SeatStatus seatStatus) {
        this.seatStatus = seatStatus;
    }

    @Column(name = "price", nullable = false)
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Column(name = "spent_points", nullable = false)
    public int getSpentPoints() {
        return spentPoints;
    }

    public void setSpentPoints(int spentBonuses) {
        this.spentPoints = spentBonuses;
    }
}
