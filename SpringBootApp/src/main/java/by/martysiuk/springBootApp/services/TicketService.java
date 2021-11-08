package by.martysiuk.springBootApp.services;

import by.martysiuk.springBootApp.models.Ticket;

import java.util.List;

public interface TicketService {
    List<Ticket> showTickets();
    void saveTicket(Ticket ticket);
    List<Ticket> showTicketsByUsername(String username);
    void deleteTicket(int id);
    List<Ticket> showSeats(String date, int id);
}
