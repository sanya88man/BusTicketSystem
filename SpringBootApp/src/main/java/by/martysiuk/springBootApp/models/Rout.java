package by.martysiuk.springBootApp.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "routs")
public class Rout {

    private int id;

    private String name;

    private double price;

    @OneToMany(mappedBy = "rout", fetch = FetchType.LAZY)
    private List<Ticket> ticketList;

    public Rout() {}

    public Rout(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
