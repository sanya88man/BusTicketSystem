package by.martysiuk.springBootApp.dao;

import by.martysiuk.springBootApp.models.User;
import by.martysiuk.springBootApp.models.UserRole;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User showUserByUsername(String username) {
        return sessionFactory.getCurrentSession().get(User.class, username);
    }

    @Override
    public void saveUser(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole("ROLE_USER");

        user.setPassword(encodedPassword);
        user.setEnabled(true);

        sessionFactory.getCurrentSession().persist(user);
        sessionFactory.getCurrentSession().persist(userRole);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> showUsers() {
        return sessionFactory.getCurrentSession().createQuery("from User").list();
    }

    @Override
    public void deleteUser(String username) {
        User user = sessionFactory.getCurrentSession().get(User.class, username);
        sessionFactory.getCurrentSession().delete(user);
    }

    @Override
    public void addAdmin(String username) {
        User user = sessionFactory.getCurrentSession().get(User.class, username);
        UserRole userRole = new UserRole(user, "ROLE_ADMIN");
        sessionFactory.getCurrentSession().persist(userRole);
    }

    @Override
    public void unblockUser(User user) {
        user = sessionFactory.getCurrentSession().get(User.class, user.getUsername());
        user.setEnabled(true);
        sessionFactory.getCurrentSession().update(user);
    }

    @Override
    public void blockUser(String username) {
        User user = sessionFactory.getCurrentSession().get(User.class, username);
        user.setEnabled(false);
        sessionFactory.getCurrentSession().update(user);
    }
}