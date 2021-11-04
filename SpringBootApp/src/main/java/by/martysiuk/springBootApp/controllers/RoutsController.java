package by.martysiuk.springBootApp.controllers;

import by.martysiuk.springBootApp.dao.RoutDao;
import by.martysiuk.springBootApp.models.Rout;
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
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Controller
public class RoutsController {

    RoutDao routDao;

    @Autowired
    public RoutsController(RoutDao routDao) {
        this.routDao = routDao;
    }

    @GetMapping("/routs")
    public String showRouts(Model model) {
        List<Rout> routList = routDao.showRouts();
        model.addAttribute("routList", routList);
        return "routs/showRouts";
    }

    @GetMapping("/admin/routs/edit")
    public String showRoutsForEdit(Model model) {
        List<Rout> routList = routDao.showRouts();
        model.addAttribute("routList", routList);
        return "routs/showRoutsForEdit";
    }

    @GetMapping("/admin/routs/{id}/editRout")
    public String editRout(Model model, @PathVariable("id") int id) {
        Rout rout = routDao.showRout(id);
        model.addAttribute("rout", rout);
        //model.addAttribute("id", id);
        return "routs/editRout";
    }

    @PatchMapping("/admin/routs/{id}")
    public String updateRout(@ModelAttribute("rout") @Valid Rout rout,
                             BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "routs/editRout";
        }

        routDao.updateRout(rout);
        return "redirect:/routs";
    }

    @GetMapping("/user/routs/{id}")
    public String showRout(@PathVariable("id") int id, Model model,
                           GregorianCalendar calendar,
                           SimpleDateFormat simpleDateFormat) {
        String s1;
        String s2;
        String s3;

        model.addAttribute("rout", routDao.showRout(id));
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

        return "routs/showRout";
    }
}
