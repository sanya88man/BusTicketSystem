package by.martysiuk.springBootApp.dao;

import by.martysiuk.springBootApp.models.Rout;
import by.martysiuk.springBootApp.models.Ticket;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class TicketDaoImpl implements TicketDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public TicketDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    @Override
    public List<Ticket> showSeats(String date, int id) {
        String hql = "from Ticket where date = :date and rout_id = :id";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);

        query.setParameter("date", date);
        query.setParameter("id", id);

        List<Ticket> ticketList= query.list();

        return ticketList;
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

    @Transactional
    @Override
    public List<Ticket> loadTickets() {
        return sessionFactory.getCurrentSession().createQuery("from Ticket").list();
    }

    @Transactional
    @Override
    public List<Ticket> findMyTickets(String username) {
        String hql = "FROM Ticket where username = :paramName";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);

        query.setParameter("paramName", username);

        List<Ticket> ticketList = query.list();

        return ticketList;
    }


    @Transactional
    @Override
    public void saveTicket(Ticket ticket) {
        sessionFactory.getCurrentSession().persist(ticket);
    }

    @Transactional
    @Override
    public Rout getRout(int service_id) {
        return sessionFactory.getCurrentSession().get(Rout.class, service_id);
    }

    @Transactional
    @Override
    public void deleteTicket(int id) {
        String hql = "DELETE Ticket WHERE id = :id";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
