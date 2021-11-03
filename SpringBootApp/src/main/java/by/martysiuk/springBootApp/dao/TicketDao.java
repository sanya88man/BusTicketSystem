package by.martysiuk.springBootApp.dao;


import by.martysiuk.springBootApp.models.Rout;
import by.martysiuk.springBootApp.models.Ticket;

import java.util.List;

public interface TicketDao {
    List<Ticket> loadTickets();
    void saveTicket(Ticket ticket);
    List<Ticket> findMyTickets(String username);
    void deleteTicket(int id);
    Rout showRout(int id);
    List<Ticket> showSeats(String date, int id);
}
