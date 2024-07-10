package Models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "\"Aircraft\"")
public class Aircraft {
    private String onBoardNumber;
    private String model;
    private int priceSeat;
    private int capacity;

    @OneToMany
    private Set<Flight> flights;

    @Id
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

    @Column(name = "price_seat", nullable = false)
    public int getPriceSeat() {
        return priceSeat;
    }

    public void setPriceSeat(int priceSeat) {
        this.priceSeat = priceSeat;
    }

    @Column(name = "capacity", nullable = false)
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

}