package services;

import models.Flight;
import models.Ticket;
import models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class TicketService {
    private static SessionFactory sessionFactory = ServiceHolder.getSessionFactory();
    private static Session session = ServiceHolder.getSession();

    public static void saveNewFlight(Ticket newTicket)
    {
        //System.out.println(newTicket.getTicket_id());
        session.save(newTicket);
    }

    public static List<Ticket> getTicketsByUser(User currentUser)
    {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Ticket> query = builder.createQuery(Ticket.class);
        Root<Ticket> root = query.from(Ticket.class);
        query.select(root).where(builder.equal(root.get("user"), currentUser));
        List<Ticket> flightList = session.createQuery(query).getResultList();
        return flightList;
    }

    public static void deleteTicket(Ticket ticket)
    {
        session.delete(ticket);
    }

    public static Ticket getTicketById(int id)
    {
        return session.get(Ticket.class, id);
    }

}
