package Models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "\"Status\"")
public class Status {
    private int id;
    private String name;
    private int price;

    @OneToMany
    Set<Human> humans;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false, length = 10)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "price", nullable = false)
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}