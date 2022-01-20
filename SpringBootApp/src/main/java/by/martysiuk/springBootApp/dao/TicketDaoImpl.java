package by.martysiuk.springBootApp.dao;

import by.martysiuk.springBootApp.models.Ticket;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TicketDaoImpl implements TicketDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public TicketDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Ticket> showSeats(String date, int id) {
        String hql = "from Ticket where date = :date and rout_id = :id";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("date", date);
        query.setParameter("id", id);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Ticket> showTickets() {
        return sessionFactory.getCurrentSession().createQuery("from Ticket").list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Ticket> showTicketsByUsername(String username) {
        String hql = "from Ticket where username = :username";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("username", username);
        return query.list();
    }

    @Override
    public void saveTicket(Ticket ticket) {
        sessionFactory.getCurrentSession().persist(ticket);
    }

    @Override
    public void deleteTicket(int id) {
        String hql = "delete Ticket where id = :id";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
