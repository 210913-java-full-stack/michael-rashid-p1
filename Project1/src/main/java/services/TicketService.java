package services;

import models.Flight;
import models.Ticket;
import models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class TicketService {
    private static SessionFactory sessionFactory = ServiceHolder.getSessionFactory();
    private static Session session = ServiceHolder.getSession();

    public static void saveNewFlight(Ticket newTicket)
    {
        session.save(newTicket);
    }

    public static List<Ticket> getTicketsByUser(User currentUser)
    {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Ticket> query = builder.createQuery(Ticket.class);
        Root<Ticket> root = query.from(Ticket.class);
        query.select(root).where(builder.equal(root.get("user"), currentUser));
        return session.createQuery(query).getResultList();
    }

    public static void deleteTicket(int ticket_id)
    {
        Transaction tx = session.beginTransaction();
        Ticket currentTicket = session.get(Ticket.class,ticket_id);
        session.delete(currentTicket);
        tx.commit();
    }

    public static Ticket getTicketById(int id)
    {
        return session.get(Ticket.class, id);
    }

    public static void checkInForFlight(Ticket ticket)
    {
        ticket.setCheck_in_status(true);
        session.update(ticket);
    }

    public static List<Ticket> getTicketList()
    {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Ticket> query = builder.createQuery(Ticket.class);
        query.from(Ticket.class);
        List<Ticket> ticketList = session.createQuery(query).getResultList();

        return ticketList;

    }
}
