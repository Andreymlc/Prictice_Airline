package com.practice.airline.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "status")
public class Status extends BaseEntity{
    private String name;
    private double discount;
    private double coefficientPoints;

    public Status(String name, double discount, double coefficientPoints) {
        setName(name);
        setDiscount(discount);
        setCoefficientPoints(coefficientPoints);
    }

    protected Status() {}

    @Column(name = "name", nullable = false, length = 10)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "discount", nullable = false)
    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Column(name = "experience_bonus", nullable = false)
    public double getCoefficientPoints() {
        return coefficientPoints;
    }

    public void setCoefficientPoints(double coefficientPoints) {
        this.coefficientPoints = coefficientPoints;
    }
}
