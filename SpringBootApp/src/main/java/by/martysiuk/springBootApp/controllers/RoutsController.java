package by.martysiuk.springBootApp.controllers;

import by.martysiuk.springBootApp.models.Rout;
import by.martysiuk.springBootApp.services.RoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

@Controller
public class RoutsController {
    private final RoutService routService;

    @Autowired
    public RoutsController(RoutService routService) {
        this.routService = routService;
    }

    @GetMapping("/routs")
    public String showRouts(Model model) {
        model.addAttribute("routList", routService.showRouts());
        return "routs/showRouts";
    }

    @GetMapping("/admin/routs/edit")
    public String showRoutsForEdit(Model model) {
        model.addAttribute("routList", routService.showRouts());
        return "routs/showRoutsForEdit";
    }

    @GetMapping("/admin/routs/{id}/editRout")
    public String editRout(Model model, @PathVariable("id") int id) {
        model.addAttribute("rout", routService.showRout(id));
        return "routs/editRout";
    }

    @PatchMapping("/admin/routs/{id}")
    public String updateRout(@ModelAttribute("rout") @Valid Rout rout,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "routs/editRout";
        }
        routService.updateRout(rout);
        return "redirect:/routs";
    }

    @GetMapping("/user/routs/{id}")
    public String showRout(@PathVariable("id") int id, Model model,
                           GregorianCalendar calendar,
                           SimpleDateFormat simpleDateFormat) {
        String[] arr = routService.showDates(calendar, simpleDateFormat);
        model.addAttribute("rout", routService.showRout(id));
        model.addAttribute("id", id);
        model.addAttribute("s1", arr[0]);
        model.addAttribute("s2", arr[1]);
        model.addAttribute("s3", arr[2]);
        return "routs/showRout";
    }
}
