package by.martysiuk.springBootApp.services;

import by.martysiuk.springBootApp.models.User;
import by.martysiuk.springBootApp.models.UserRole;

import java.util.List;

public interface UserService {
    User showUserByUsername(String username);

    void saveUser(User user);

    List<User> showUsers();

    void deleteUser(String username);

    void addAdmin(String username);

    void unblockUser(User user);

    void blockUser(String username);

    List<UserRole> showRoles();
}
