package by.martysiuk.springBootApp.dao;

import by.martysiuk.springBootApp.models.Rout;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class RoutDaoImpl implements RoutDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public RoutDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    @Override
    public Rout showRout(int id) {
        return sessionFactory.getCurrentSession().get(Rout.class, id);
    }

    @Transactional
    @Override
    public List<Rout> loadRouts() {
        return sessionFactory.getCurrentSession().createQuery("from Rout ").list();
    }
}
