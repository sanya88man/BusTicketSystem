package by.martysiuk.springBootApp.dao;

import by.martysiuk.springBootApp.models.User;
import java.util.List;

public interface UserDao {
    User findByUserName(String username);

    void saveUser(User user);

    List<User> findAll();

    void deleteUser(String username);

    void addAdmin(String username);
}