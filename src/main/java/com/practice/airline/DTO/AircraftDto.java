package com.practice.airline.DTO;

import com.practice.airline.domain.Flight;

import java.util.Set;

public class AircraftDto {
    private Long id;
    private String onBoardNumber;
    private String model;
    private int capacity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOnBoardNumber() {
        return onBoardNumber;
    }

    public void setOnBoardNumber(String onBoardNumber) {
        this.onBoardNumber = onBoardNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
