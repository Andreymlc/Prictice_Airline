package com.practice.airline.DTO;

public class StatusDto {
    private String name;
    private double discount;
    private double coefficientPoints;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getCoefficientPoints() {
        return coefficientPoints;
    }

    public void setCoefficientPoints(double coefficientPoints) {
        this.coefficientPoints = coefficientPoints;
    }
}
