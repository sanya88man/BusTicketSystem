package by.martysiuk.springBootApp.dao;

import by.martysiuk.springBootApp.models.User;

import java.util.List;

public interface UserDao {
    User showUserByUsername(String username);

    void saveUser(User user);

    List<User> showUsers();

    void deleteUser(String username);

    void addAdmin(String username);
}