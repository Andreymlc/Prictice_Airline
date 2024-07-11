package com.practice.airline.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "seat_status")
public class SeatStatus extends BaseEntity {
    private String name;
    private int price;

    public SeatStatus(String name, int price) {
        this.name = name;
        this.price = price;
    }

    protected SeatStatus() {}

    @Column(name = "price", nullable = false)
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Column(name = "name", nullable = false, length = 10)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
