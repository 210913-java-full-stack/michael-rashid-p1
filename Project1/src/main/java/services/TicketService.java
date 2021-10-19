package services;

import models.Flight;
import models.Ticket;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class TicketService {
    private static SessionFactory sessionFactory = ServiceHolder.getSessionFactory();
    private static Session session = ServiceHolder.getSession();

    public static void saveNewFlight(Ticket newTicket)
    {
        session.save(newTicket);
    }

}
