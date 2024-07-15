package com.practice.airline.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.Set;

@Entity
@Table(name = "human")
public class Human extends BaseEntity {
    private String fullName;
    private String phoneNumber;
    private String passportId;
    private Status status;
    private int experience;
    private int points;

    public Human(String fullName, String phoneNumber, String passportId, Status status, int experience, int points) {
        setFullName(fullName);
        setPhoneNumber(phoneNumber);
        setPassportId(passportId);
        setStatus(status);
        setExperience(experience);
        setPoints(points);
    }

    protected Human() {}

    @Column(name = "full_name", nullable = false)
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Column(name = "phone_number", nullable = false, length = 20)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Column(name = "passport_id", nullable = false, length = 10)
    public String getPassportId() {
        return passportId;
    }

    public void setPassportId(String passportId) {
        this.passportId = passportId;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "status_id", nullable = false)
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Column(name = "experience", nullable = false)
    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    @Column(name = "points", nullable = false)
    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
