package com.practice.airline.DTO;

public class AddAircraftDto {
    private String onBoardNumber;
    private String model;
    private int capacity;

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
