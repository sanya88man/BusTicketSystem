package by.martysiuk.springBootApp.dao;

import by.martysiuk.springBootApp.models.User;
import by.martysiuk.springBootApp.models.UserRole;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public User findByUserName(String username) {
        return sessionFactory.getCurrentSession().get(User.class, username);
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

        sessionFactory.getCurrentSession().persist(user);
        sessionFactory.getCurrentSession().persist(userRole);
    }

    @Transactional
    @Override
    public List<User> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from User").list();
    }

    @Transactional
    @Override
    public void deleteUser(String username) {
        User user = sessionFactory.getCurrentSession().get(User.class, username);
        sessionFactory.getCurrentSession().delete(user);
    }

    @Transactional
    @Override
    public void addAdmin(String username) {
        User user = sessionFactory.getCurrentSession().get(User.class, username);
        UserRole userRole = new UserRole(user, "ROLE_ADMIN");
        sessionFactory.getCurrentSession().persist(userRole);
    }

}