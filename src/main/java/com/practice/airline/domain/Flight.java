package com.practice.airline.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "flight")
public class Flight extends BaseEntity {
    private Aircraft aircraft_id;
    private Airport departureAirport;
    private Airport arrivalAirport;
    private LocalDateTime departureDate;
    private LocalDateTime  arrivalDate;
    private int price;
    private int cntEconomySeat;
    private int cntBusinessSeat;
    private Set<Booking> bookings;

    public Flight(Aircraft aircraft_id, Airport departureAirport, Airport arrivalAirport, LocalDateTime  departureDate, LocalDateTime  arrivalDate, int price, int cntEconomySeat, int cntBusinessSeat, Set<Booking> bookings) {
        setAircraft_id(aircraft_id);
        setDepartureAirport(departureAirport);
        setArrivalAirport(arrivalAirport);
        setDepartureDate(departureDate);
        setArrivalDate(arrivalDate);
        setPrice(price);
        setCntEconomySeat(cntEconomySeat);
        setCntBusinessSeat(cntBusinessSeat);
        setBookings(bookings);
    }

    protected Flight() {}

    @ManyToOne
    @JoinColumn(name = "on_board_number", nullable = false)
    public Aircraft getAircraft_id() {
        return aircraft_id;
    }

    public void setAircraft_id(Aircraft onBoardNumber) {
        this.aircraft_id = onBoardNumber;
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

    @OneToMany(mappedBy = "flight")
    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }
}
