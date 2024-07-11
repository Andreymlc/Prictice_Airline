package com.practice.airline.Entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "aircraft")
public class Aircraft extends BaseEntity {
    private String onBoardNumber;
    private String model;
    private int capacity;
    private Set<Flight> flights;

    public Aircraft(String model, int capacity, Set<Flight> flights) {
        setModel(model);
        setCapacity(capacity);
        setFlights(flights);
    }

    protected Aircraft() {}

    @Column(name = "on_board_number", nullable = false, length = 10)
    public String getOnBoardNumber() {
        return onBoardNumber;
    }

    public void setOnBoardNumber(String onBoardNumber) {
        this.onBoardNumber = onBoardNumber;
    }

    @Column(name = "model", nullable = false, length = 20)
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Column(name = "capacity", nullable = false)
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
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
