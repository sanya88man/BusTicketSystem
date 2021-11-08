package by.martysiuk.springBootApp.services;

import by.martysiuk.springBootApp.dao.TicketDao;
import by.martysiuk.springBootApp.models.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
    public List<Ticket> showSeats(String date, int id) {
        List<Ticket> ticketList = new ArrayList<>();

        final int SEATS_AMOUNT = 20;
        int k = 0;
        int count = 0;

        for (int i = 0; i < SEATS_AMOUNT; i++) {
            Ticket ticket1 = new Ticket();
            ticketList.add(ticket1);
            ticketList.get(i).setSeat(i + 1);
        }

        List<Ticket> tickets = ticketDao.showSeats(date, id);

        for (Ticket ticket1 : ticketList) {
            for (Ticket ticket2 : tickets) {
                if (ticket1.getSeat() == ticket2.getSeat()) {
                    ticket1.setSeat(-1);
                }
            }
        }

        while (count != SEATS_AMOUNT) {
            if (ticketList.get(k).getSeat() == -1) {
                ticketList.remove(ticketList.get(k));
            } else {
                k++;
            }
            count++;
        }

        return ticketList;
    }
}
