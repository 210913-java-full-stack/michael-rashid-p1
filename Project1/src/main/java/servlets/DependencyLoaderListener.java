package servlets;


import models.Flight;
import models.Ticket;
import models.User;
import org.hibernate.cfg.Configuration;
import services.ServiceHolder;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DependencyLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Configuration config = new Configuration();
        config.addAnnotatedClass(Flight.class);
        config.addAnnotatedClass(Ticket.class);
        config.addAnnotatedClass(User.class);

        //service holder holders both the session factory and session. I'm calling the setters here
        //to actually use the config to build them.
        ServiceHolder.setSessionFactory(config.buildSessionFactory());
        ServiceHolder.setSession(ServiceHolder.getSessionFactory().openSession());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //use the getter to grab the stored session and then close it
        ServiceHolder.getSession().close();
    }
}