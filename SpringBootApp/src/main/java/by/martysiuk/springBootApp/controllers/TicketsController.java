package by.martysiuk.springBootApp.controllers;

import by.martysiuk.springBootApp.dao.TicketDao;
import by.martysiuk.springBootApp.models.Rout;
import by.martysiuk.springBootApp.models.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class TicketsController {

    private final TicketDao ticketDao;

    @Autowired
    public TicketsController(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    @GetMapping("/show-routs")
    public String listRouts(Model model /*, @ModelAttribute("routList") Rout rout*/) {
        List<Rout> routList = ticketDao.loadRouts();
        model.addAttribute("routList", routList);
        return "user/show-routs";
    }

    @GetMapping("/user/{id}")
    public String showRout(@PathVariable("id") int id, Model model,
                           GregorianCalendar calendar, SimpleDateFormat simpleDateFormat) {

        String s1;
        String s2;
        String s3;

        model.addAttribute("rout", ticketDao.showRout(id));
        model.addAttribute("id", id);

        simpleDateFormat.applyPattern("dd-MM-yyyy");

        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        s1 = simpleDateFormat.format(calendar.getTime());

        calendar.add(Calendar.DAY_OF_MONTH, 1);
        s2 = simpleDateFormat.format(calendar.getTime());

        calendar.add(Calendar.DAY_OF_MONTH, 1);
        s3 = simpleDateFormat.format(calendar.getTime());

        model.addAttribute("s1", s1);
        model.addAttribute("s2", s2);
        model.addAttribute("s3", s3);

        return "user/show-rout";
    }

    @GetMapping("/user/{id}/{date}")
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
        Rout rout = ticketDao.getRout(id);

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

        return "user/show-seats";
    }

    @PostMapping("/user/add-ticket")
    String addTicket(@ModelAttribute("ticket") Ticket ticket, Model model,
                     HttpServletRequest httpServletRequest) {

        Rout rout = ticketDao.getRout(ticket.getRout_id());
        model.addAttribute("rout", rout);

        ticket.setUsername(httpServletRequest.getRemoteUser());

        ticketDao.saveTicket(ticket);

        return "user/show-ticket";
    }

    @GetMapping("/user/my-tickets")
    String showMyTickets(Model model, HttpServletRequest httpServletRequest) {
        List<Ticket> tickets = ticketDao.findMyTickets(httpServletRequest.getRemoteUser());
        model.addAttribute("tickets", tickets);
        return "user/my-tickets";
    }

    @GetMapping("/admin/all-orders")
    String showAllTickets(Model model) {
        List<Ticket> tickets = ticketDao.loadTickets();
        model.addAttribute("tickets", tickets);
        return "admin/all-orders";
    }

    @PostMapping("/admin/delete-ticket")
    String deleteTicket(@ModelAttribute("ticket") Ticket ticket) {
        ticketDao.deleteTicket(ticket.getId());
        return "redirect:/admin/all-orders";
    }

    @PostMapping("/user/delete-ticket")
    String deleteUserTicket(@ModelAttribute("ticket") Ticket ticket) {
        ticketDao.deleteTicket(ticket.getId());
        return "redirect:/user/my-tickets";
    }
}
