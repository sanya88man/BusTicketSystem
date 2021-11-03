package by.martysiuk.springBootApp.dao;

import by.martysiuk.springBootApp.models.Ticket;

import java.util.List;

public interface TicketDao {
    List<Ticket> showTickets();
    void saveTicket(Ticket ticket);
    List<Ticket> showTicketsByUsername(String username);
    void deleteTicket(int id);
    List<Ticket> showSeats(String date, int id);
}
