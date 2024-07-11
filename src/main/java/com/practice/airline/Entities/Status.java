package com.practice.airline.Entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "status")
public class Status extends BaseEntity{
    private String name;
    private double discount;
    Set<Human> humans;

    public Status(String name, double discount, Set<Human> humans) {
        setName(name);
        setDiscount(discount);
        setHumans(humans);
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

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    public Set<Human> getHumans() {
        return humans;
    }

    public void setHumans(Set<Human> humans) {
        this.humans = humans;
    }
}
