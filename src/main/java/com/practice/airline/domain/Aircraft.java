package com.practice.airline.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "aircraft")
public class Aircraft extends BaseEntity {
    private String onBoardNumber;
    private String model;
    private int capacity;

    public Aircraft(String onBoardNumber ,String model, int capacity) {
        setOnBoardNumber(onBoardNumber);
        setModel(model);
        setCapacity(capacity);
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
}
