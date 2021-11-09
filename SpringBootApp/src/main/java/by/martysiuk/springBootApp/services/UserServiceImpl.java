package by.martysiuk.springBootApp.services;

import by.martysiuk.springBootApp.dao.UserDao;
import by.martysiuk.springBootApp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
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
        userDao.saveUser(user);
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
}
