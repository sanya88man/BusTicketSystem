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
        return "users/403";
    }

    @GetMapping("/users/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "users/newUser";
    }

    @PostMapping("/users/processRegister")
    public String createUser(@ModelAttribute("user") User user) {

        if (userDao.findByUserName(user.getUsername()) != null) {
            return "users/errAddUser";
        }

        userDao.saveUser(user);
        return "users/registerSuccess";
    }

    @GetMapping("/admin/users")
    public String showUsers(Model model, @ModelAttribute("user1") User user) {
        List<User> listUsers = userDao.findAll();
        model.addAttribute("listUsers", listUsers);
        return "users/showUsers";
    }

    @PostMapping("/admin/users/delete")
    public String deleteUser(@ModelAttribute("user1") User user) {

        if (userDao.findByUserName(user.getUsername()) == null) {
            return "users/userNotExist";
        }

        userDao.deleteUser(user.getUsername());
        return "redirect:/admin/users";
    }

    @PostMapping("admin/users/newAdmin")
    public String addAdmin(@ModelAttribute("user1") User user) {

        if (userDao.findByUserName(user.getUsername()) == null) {
            return "users/userNotExist";
        }

        userDao.addAdmin(user.getUsername());
        return "redirect:/admin/users";
    }

}
