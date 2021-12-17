package by.martysiuk.springBootApp.controllers;

import by.martysiuk.springBootApp.models.User;
import by.martysiuk.springBootApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UsersController {
    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index() {
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
    public String showUsers(Model model, @ModelAttribute("user") User user) {
        model.addAttribute("listUsers", userService.showUsers());
        model.addAttribute("listRoles", userService.showRoles());
        return "users/showUsers";
    }

    @DeleteMapping("/admin/users/delete")
    public String deleteUser(@ModelAttribute("user") User user) {
        if (userService.showUserByUsername(user.getUsername()) == null) {
            return "users/userNotExist";
        }
        userService.deleteUser(user.getUsername());
        return "redirect:/admin/users";
    }

    @PostMapping("admin/users/newAdmin")
    public String addAdmin(@ModelAttribute("user") User user) {
        if (userService.showUserByUsername(user.getUsername()) == null) {
            return "users/userNotExist";
        }
        userService.addAdmin(user.getUsername());
        return "redirect:/admin/users";
    }

    @PatchMapping("admin/users/block")
    public String blockUser(@ModelAttribute("user") User user) {
        if (userService.showUserByUsername(user.getUsername()) == null) {
            return "users/userNotExist";
        }
        userService.blockUser(user.getUsername());
        return "redirect:/admin/users";
    }

    @PatchMapping("admin/users/unblock")
    public String unblockUser(@ModelAttribute("user") User user) {
        if (userService.showUserByUsername(user.getUsername()) == null) {
            return "users/userNotExist";
        }
        userService.unblockUser(user);
        return "redirect:/admin/users";
    }
}
