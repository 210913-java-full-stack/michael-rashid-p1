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

        ServiceHolder.setSessionFactory(config.buildSessionFactory());
        ServiceHolder.setSession(ServiceHolder.getSessionFactory().openSession());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServiceHolder.getSession().close();
    }
}