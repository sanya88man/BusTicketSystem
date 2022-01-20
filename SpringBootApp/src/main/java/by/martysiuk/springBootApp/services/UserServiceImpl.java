package by.martysiuk.springBootApp.services;

import by.martysiuk.springBootApp.dao.UserDao;
import by.martysiuk.springBootApp.models.User;
import by.martysiuk.springBootApp.models.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public User showUserByUsername(String username) {
        return userDao.showUserByUsername(username);
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole("ROLE_USER");

        user.setPassword(encodedPassword);
        user.setEnabled(true);
        userDao.saveUser(user, userRole);
    }

    @Transactional
    @Override
    public List<User> showUsers() {
        return userDao.showUsers();
    }

    @Transactional
    @Override
    public void deleteUser(String username) {
        userDao.deleteUser(username);
    }

    @Transactional
    @Override
    public void addAdmin(String username) {
        userDao.addAdmin(username);
    }

    @Transactional
    @Override
    public void unblockUser(User user) {
        userDao.unblockUser(user);
    }

    @Transactional
    @Override
    public void blockUser(String username) {
        userDao.blockUser(username);
    }

    @Transactional
    @Override
    public List<UserRole> showRoles() {
        return userDao.showRoles();
    }
}
