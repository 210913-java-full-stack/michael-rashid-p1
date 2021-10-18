package services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ServiceHolder {
private static SessionFactory sessionFactory;
private static Session session;

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void setSessionFactory(SessionFactory sessionFactory) {
        ServiceHolder.sessionFactory = sessionFactory;
    }

    public static Session getSession() {
        return session;
    }

    public static void setSession(Session session) {
        ServiceHolder.session = session;
    }
}
