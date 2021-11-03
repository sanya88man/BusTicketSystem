package by.martysiuk.springBootApp.controllers;

import by.martysiuk.springBootApp.dao.TicketDao;
import by.martysiuk.springBootApp.models.Rout;
import by.martysiuk.springBootApp.models.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class TicketsController {

    private final TicketDao ticketDao;

    @Autowired
    public TicketsController(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    @GetMapping("/user/tickets/{id}/{date}")
    String showSeats(@PathVariable(value = "date") String date,
                     @PathVariable(value = "id") int id, Model model) {
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
        Rout rout = ticketDao.showRout(id);

        for (Ticket ticket1 : ticketList) {
            for (Ticket ticket2 : tickets) {
                if (ticket1.getSeat() == ticket2.getSeat()) {
                    ticket1.setSeat(-1);
                }
            }
        }

        while (count != SEATS_AMOUNT){
            if (ticketList.get(k).getSeat() == -1) {
                ticketList.remove(ticketList.get(k));
            } else {
                k++;
            }
            count++;
        }

        model.addAttribute("rout", rout);
        model.addAttribute("ticketList", ticketList);
        model.addAttribute("id", id);
        model.addAttribute("date", date);

        return "tickets/showSeats";
    }

    @PostMapping("/user/tickets/new")
    String addTicket(@ModelAttribute("ticket") Ticket ticket, Model model,
                     HttpServletRequest httpServletRequest) {
        Rout rout = ticketDao.showRout(ticket.getRout_id());
        model.addAttribute("rout", rout);

        ticket.setUsername(httpServletRequest.getRemoteUser());

        ticketDao.saveTicket(ticket);

        return "tickets/showNewTicket";
    }

    @GetMapping("/user/tickets/showMyTickets")
    String showMyTickets(Model model, HttpServletRequest httpServletRequest) {
        List<Ticket> tickets = ticketDao.findMyTickets(httpServletRequest.getRemoteUser());
        model.addAttribute("tickets", tickets);
        return "tickets/showMyTickets";
    }

    @GetMapping("/admin/tickets")
    String showAllTickets(Model model) {
        List<Ticket> tickets = ticketDao.loadTickets();
        model.addAttribute("tickets", tickets);
        return "tickets/showAllTickets";
    }

    @PostMapping("/admin/tickets/delete")
    String deleteTicket(@ModelAttribute("ticket") Ticket ticket) {
        ticketDao.deleteTicket(ticket.getId());
        return "redirect:/admin/tickets";
    }

    @PostMapping("/user/tickets/delete")
    String deleteUserTicket(@ModelAttribute("ticket") Ticket ticket) {
        ticketDao.deleteTicket(ticket.getId());
        return "redirect:/user/tickets/showMyTickets";
    }
}
