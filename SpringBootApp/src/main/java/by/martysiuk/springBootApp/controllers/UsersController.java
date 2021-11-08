package by.martysiuk.springBootApp.controllers;

import by.martysiuk.springBootApp.models.User;
import by.martysiuk.springBootApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/")
    public String showUsers() {
        return "users/index";
    }

    @GetMapping("/403")
    public String error403() {
        return "users/403";
    }

    @GetMapping("/users/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "users/newUser";
    }

    @PostMapping("/users/processRegister")
    public String createUser(@ModelAttribute("user") User user) {
        if (userService.showUserByUsername(user.getUsername()) != null) {
            return "users/errAddUser";
        }

        userService.saveUser(user);
        return "users/registerSuccess";
    }

    @GetMapping("/admin/users")
    public String showUsers(Model model, @ModelAttribute("user1") User user) {
        model.addAttribute("listUsers", userService.showUsers());
        return "users/showUsers";
    }

    @DeleteMapping("/admin/users/delete")
    public String deleteUser(@ModelAttribute("user1") User user) {
        if (userService.showUserByUsername(user.getUsername()) == null) {
            return "users/userNotExist";
        }

        userService.deleteUser(user.getUsername());
        return "redirect:/admin/users";
    }

    @PostMapping("admin/users/newAdmin")
    public String addAdmin(@ModelAttribute("user1") User user) {
        if (userService.showUserByUsername(user.getUsername()) == null) {
            return "users/userNotExist";
        }

        userService.addAdmin(user.getUsername());
        return "redirect:/admin/users";
    }
}
