package by.martysiuk.springBootApp.models;

import javax.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "seat")
    private int seat;

    @Column(name = "date")
    String date;

    @Column(name = "rout_id")
    private int rout_id;

    @Column(name = "username")
    private String username;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rout_id", insertable = false, updatable = false)
    private Rout rout;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", insertable = false, updatable = false)
    private User user;

    public Ticket() {}

    public Ticket(int id, int seat, String date, int rout_id, String username, Rout rout, User user) {
        this.id = id;
        this.seat = seat;
        this.date = date;
        this.rout_id = rout_id;
        this.username = username;
        this.rout = rout;
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket)) return false;
        Ticket ticket = (Ticket) o;
        return getSeat() == ticket.getSeat();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSeat());
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getRout_id() {
        return rout_id;
    }

    public void setRout_id(int rout_id) {
        this.rout_id = rout_id;
    }

    public Rout getRout() {
        return rout;
    }

    public void setRout(Rout rout) {
        this.rout = rout;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int order_id) {
        this.id = order_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
