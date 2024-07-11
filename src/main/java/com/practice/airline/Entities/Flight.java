package com.practice.airline.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "flight")
public class Flight extends BaseEntity {
    private Aircraft onBoardNumber;
    private Airport departureAirport;
    private Airport arrivalAirport;
    private LocalDate departureDate;
    private LocalDate arrivalDate;
    private int price;
    private int cntEconomySeat;
    private int cntBusinessSeat;
    private Set<Booking> bookings;

    public Flight(Aircraft onBoardNumber, Airport departureAirport, Airport arrivalAirport, LocalDate departureDate, LocalDate arrivalDate, int price, int cntEconomySeat, int cntBusinessSeat, Set<Booking> bookings) {
        setOnBoardNumber(onBoardNumber);
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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "on_board_number", nullable = false)
    public Aircraft getOnBoardNumber() {
        return onBoardNumber;
    }

    public void setOnBoardNumber(Aircraft onBoardNumber) {
        this.onBoardNumber = onBoardNumber;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "departure_airport_id", nullable = false)
    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "arrival_airport_id", nullable = false)
    public Airport getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(Airport arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    @Column(name = "departure_date", nullable = false)
    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    @Column(name = "arrival_date", nullable = false)
    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
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

    @OneToMany(mappedBy = "flight", fetch = FetchType.LAZY)
    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }
}
