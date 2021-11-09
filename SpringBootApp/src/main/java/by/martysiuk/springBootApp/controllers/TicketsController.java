package by.martysiuk.springBootApp.controllers;

import by.martysiuk.springBootApp.models.Ticket;
import by.martysiuk.springBootApp.services.RoutService;
import by.martysiuk.springBootApp.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TicketsController {
    private final TicketService ticketService;
    private final RoutService routService;

    @Autowired
    public TicketsController(TicketService ticketService, RoutService routService) {
        this.ticketService = ticketService;
        this.routService = routService;
    }

    @GetMapping("/user/tickets/{id}/{date}")
    String showSeats(@PathVariable(value = "date") String date,
                     @PathVariable(value = "id") int id, Model model) {
        model.addAttribute("rout", routService.showRout(id));
        model.addAttribute("date", date);
        model.addAttribute("ticketList", ticketService.showSeats(date, id));
        return "tickets/showSeats";
    }

    @PostMapping("/user/tickets")
    String createTicket(@ModelAttribute("ticket") Ticket ticket,
                        HttpServletRequest httpServletRequest) {
        ticket.setUsername(httpServletRequest.getRemoteUser());
        ticketService.saveTicket(ticket);
        return "redirect:/routs";
    }

    @PostMapping("/user/tickets/new")
    String newTicket(@ModelAttribute("ticket") Ticket ticket, Model model) {
        model.addAttribute("rout", routService.showRout(ticket.getRout_id()));
        return "tickets/showTicket";
    }

    @GetMapping("/user/tickets/showMyTickets")
    String showMyTickets(Model model, HttpServletRequest httpServletRequest) {
        model.addAttribute("tickets",
                ticketService.showTicketsByUsername(httpServletRequest.getRemoteUser()));
        return "tickets/showMyTickets";
    }

    @GetMapping("/admin/tickets")
    String showTickets(Model model) {
        model.addAttribute("tickets", ticketService.showTickets());
        return "tickets/showAllTickets";
    }

    @DeleteMapping("/admin/tickets/delete")
    String deleteTicketByAdmin(@ModelAttribute("ticket") Ticket ticket) {
        ticketService.deleteTicket(ticket.getId());
        return "redirect:/admin/tickets";
    }

    @DeleteMapping("/user/tickets/delete")
    String deleteTicketByUser(@ModelAttribute("ticket") Ticket ticket) {
        ticketService.deleteTicket(ticket.getId());
        return "redirect:/user/tickets/showMyTickets";
    }
}
