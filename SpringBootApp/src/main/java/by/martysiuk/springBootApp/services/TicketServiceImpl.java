package by.martysiuk.springBootApp.services;

import by.martysiuk.springBootApp.dao.TicketDao;
import by.martysiuk.springBootApp.models.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TicketServiceImpl implements TicketService {
    private final TicketDao ticketDao;

    @Autowired
    public TicketServiceImpl(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    @Transactional
    @Override
    public List<Ticket> showTickets() {
        return ticketDao.showTickets();
    }

    @Transactional
    @Override
    public void saveTicket(Ticket ticket) {
        ticketDao.saveTicket(ticket);
    }

    @Transactional
    @Override
    public List<Ticket> showTicketsByUsername(String username) {
        return ticketDao.showTicketsByUsername(username);
    }

    @Transactional
    @Override
    public void deleteTicket(int id) {
        ticketDao.deleteTicket(id);
    }

    @Transactional
    @Override
    public Set<Ticket> showSeats(String date, int id) {
        final int SEATS_AMOUNT = 20;
        Set<Ticket> ticketSet = new HashSet<>(20);
        List<Ticket> orderedTickets = ticketDao.showSeats(date, id);

        for (int i = 0; i < SEATS_AMOUNT; i++) {
            Ticket ticket = new Ticket();
            ticket.setSeat(i + 1);
            ticketSet.add(ticket);
        }
        orderedTickets.forEach(ticketSet::remove);
        return ticketSet;
    }
}
