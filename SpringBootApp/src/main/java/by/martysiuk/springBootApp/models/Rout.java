package by.martysiuk.springBootApp.models;

import javax.validation.constraints.*;

import javax.persistence.*;
/*import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;*/
import java.util.List;

@Entity
@Table(name = "routs")
public class Rout {

    private int id;

    @NotEmpty(message = "Rout name should not be empty!")
    @Size(min = 6, max = 20, message = "Rout name should be 6-20 symbols!")
    @Column(name = "name")
    private String name;

    @Min(value = 5, message = "Price should be in (5-99)!")
    @Max(value = 99, message = "Price should be in (5-99)!")
    @Column(name = "price")
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
