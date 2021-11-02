package by.martysiuk.springBootApp.controllers;

import by.martysiuk.springBootApp.dao.UserDao;
import by.martysiuk.springBootApp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UsersController {

    private final UserDao userDao;

    @Autowired
    public UsersController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/")
    public String index() {
        return "enter/index";
    }

    @GetMapping("/403")
    public String err403() {
        return "enter/403";
    }

    @GetMapping("/register")
    public String showRegistrationForm(@ModelAttribute("user") User user) {
        return "enter/signup_form";
    }

    @PostMapping("/process_register")
    public String processRegister(@ModelAttribute("user") User user) {

        if (userDao.findByUserName(user.getUsername()) != null) {
            return "enter/erraddinguser";
        }

        userDao.saveUser(user);
        return "enter/register_success";
    }

    @GetMapping("/admin/users")
    public String listUsers(Model model, @ModelAttribute("user1") User user) {
        List<User> listUsers = userDao.findAll();
        model.addAttribute("listUsers", listUsers);
        return "admin/users";
    }

    @PostMapping("/admin/delete-user")
    public String deleteUser(@ModelAttribute("user1") User user) {

        if (userDao.findByUserName(user.getUsername()) == null) {
            return "admin/user_notexist";
        }

        userDao.deleteUser(user.getUsername());
        return "redirect:/admin/users";
    }

    @PostMapping("admin/add-admin")
    public String addAdmin(@ModelAttribute("user1") User user) {

        if (userDao.findByUserName(user.getUsername()) == null) {
            return "admin/user_notexist";
        }

        userDao.addAdmin(user.getUsername());
        return "redirect:/admin/users";
    }

}
