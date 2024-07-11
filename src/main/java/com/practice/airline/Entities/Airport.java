package com.practice.airline.Entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "airport")
public class Airport extends BaseEntity {
    private String iata;
    private String name;
    private String city;
    private Set<Flight> flights;

    public Airport(String icao, String name, String city, Set<Flight> flights) {
        setIata(icao);
        setName(name);
        setCity(city);
        setFlights(flights);
    }

    protected Airport() {}

    @Column(name = "iata", nullable = false, length = 3)
    public String getIata() {
        return iata;
    }

    public void setIata(String iata) {
        this.iata = iata;
    }

    @Column(name = "name", nullable = false, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "city", nullable = false, length = 100)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    public Set<Flight> getFlights() {
        return flights;
    }

    public void setFlights(Set<Flight> flights) {
        this.flights = flights;
    }
}
