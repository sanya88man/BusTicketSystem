package by.martysiuk.springBootApp.services;

import by.martysiuk.springBootApp.models.User;

import java.util.List;

public interface UserService {
    User showUserByUsername(String username);
    void saveUser(User user);
    List<User> showUsers();
    void deleteUser(String username);
    void addAdmin(String username);
}
