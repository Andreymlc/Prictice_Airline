package com.practice.airline.domain;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class BaseEntity {
    private long id;

    @Id
    @Column(insertable = false, name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }
    public void setId(long id) {this.id = id;}
}
