package com.practice.airline.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "flight")
public class Flight extends BaseEntity {
    private Aircraft aircraftId;
    private Airport departureAirport;
    private Airport arrivalAirport;
    private LocalDateTime departureDate;
    private LocalDateTime  arrivalDate;
    private int price;
    private int cntEconomySeat;
    private int cntBusinessSeat;

    public Flight(Aircraft aircraftId, Airport departureAirport, Airport arrivalAirport, LocalDateTime  departureDate, LocalDateTime  arrivalDate, int price, int cntEconomySeat, int cntBusinessSeat) {
        setAircraftId(aircraftId);
        setDepartureAirport(departureAirport);
        setArrivalAirport(arrivalAirport);
        setDepartureDate(departureDate);
        setArrivalDate(arrivalDate);
        setPrice(price);
        setCntEconomySeat(cntEconomySeat);
        setCntBusinessSeat(cntBusinessSeat);
    }

    protected Flight() {}

    @ManyToOne
    @JoinColumn(name = "on_board_number", nullable = false)
    public Aircraft getAircraftId() {
        return aircraftId;
    }

    public void setAircraftId(Aircraft onBoardNumber) {
        this.aircraftId = onBoardNumber;
    }

    @ManyToOne
    @JoinColumn(name = "departure_airport_id", nullable = false)
    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    @ManyToOne
    @JoinColumn(name = "arrival_airport_id", nullable = false)
    public Airport getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(Airport arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    @Column(name = "departure_date", nullable = false)
    public LocalDateTime  getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDateTime  departureDate) {
        this.departureDate = departureDate;
    }

    @Column(name = "arrival_date", nullable = false)
    public LocalDateTime  getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDateTime  arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    @Column(name = "price", nullable = false)
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Column(name = "cnt_economy_seat", nullable = false)
    public int getCntEconomySeat() {
        return cntEconomySeat;
    }

    public void setCntEconomySeat(int cntEconomySeat) {
        this.cntEconomySeat = cntEconomySeat;
    }

    @Column(name = "cnt_business_seat", nullable = false)
    public int getCntBusinessSeat() {
        return cntBusinessSeat;
    }

    public void setCntBusinessSeat(int cntBusinessSeat) {
        this.cntBusinessSeat = cntBusinessSeat;
    }
}
