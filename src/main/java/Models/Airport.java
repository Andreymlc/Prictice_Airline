package Models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "\"Airport\"")
public class Airport {
    private String icao;
    private String name;
    private String city;

    @OneToMany
    private Set<Flight> flights;

    @Id
    @Column(name = "icao", nullable = false, length = 4)
    public String getIcao() {
        return icao;
    }

    public void setIcao(String icao) {
        this.icao = icao;
    }

    @Column(name = "name", nullable = false, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "city", nullable = false, length = 100)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}