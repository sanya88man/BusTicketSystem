package by.martysiuk.springBootApp.controllers;

import by.martysiuk.springBootApp.dao.RoutDao;
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
    private final RoutDao routDao;

    @Autowired
    public TicketsController(TicketDao ticketDao, RoutDao routDao) {
        this.ticketDao = ticketDao;
        this.routDao = routDao;
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
        Rout rout = routDao.showRout(id);

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
        model.addAttribute("date", date);
        model.addAttribute("ticketList", ticketList);

        return "tickets/showSeats";
    }

    @PostMapping("/user/tickets")
    String createTicket(@ModelAttribute("ticket") Ticket ticket,
                        HttpServletRequest httpServletRequest) {
        ticket.setUsername(httpServletRequest.getRemoteUser());
        ticketDao.saveTicket(ticket);
        return "redirect:/routs";
    }

    @PostMapping("/user/tickets/new")
    String newTicket(@ModelAttribute("ticket") Ticket ticket, Model model) {
        Rout rout = routDao.showRout(ticket.getRout_id());
        model.addAttribute("rout", rout);
        return "tickets/showTicket";
    }

    @GetMapping("/user/tickets/showMyTickets")
    String showMyTickets(Model model, HttpServletRequest httpServletRequest) {
        List<Ticket> tickets = ticketDao.showTicketsByUsername(httpServletRequest.getRemoteUser());
        model.addAttribute("tickets", tickets);
        return "tickets/showMyTickets";
    }

    @GetMapping("/admin/tickets")
    String showTickets(Model model) {
        List<Ticket> tickets = ticketDao.showTickets();
        model.addAttribute("tickets", tickets);
        return "tickets/showAllTickets";
    }

    @PostMapping("/admin/tickets/delete")
    String deleteTicketByAdmin(@ModelAttribute("ticket") Ticket ticket) {
        ticketDao.deleteTicket(ticket.getId());
         return "redirect:/admin/tickets";
    }

    @PostMapping("/user/tickets/delete")
    String deleteTicketByUser(@ModelAttribute("ticket") Ticket ticket) {
        ticketDao.deleteTicket(ticket.getId());
        return "redirect:/user/tickets/showMyTickets";
    }
}
