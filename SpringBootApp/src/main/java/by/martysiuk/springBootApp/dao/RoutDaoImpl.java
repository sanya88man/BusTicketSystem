package by.martysiuk.springBootApp.dao;

import by.martysiuk.springBootApp.models.Rout;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoutDaoImpl implements RoutDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public RoutDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Rout showRout(int id) {
        return sessionFactory.getCurrentSession().get(Rout.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Rout> showRouts() {
        return sessionFactory.getCurrentSession().createQuery("from Rout ").list();
    }

    @Override
    public void updateRout(Rout rout) {
        sessionFactory.getCurrentSession().update(rout);
    }
}
