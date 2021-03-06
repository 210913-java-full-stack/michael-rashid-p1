package services;

import models.Flight;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.*;
import java.util.List;

public class FlightService {
    private static SessionFactory sessionFactory = ServiceHolder.getSessionFactory();
    private static Session session = ServiceHolder.getSession();

    public static void init()
    {

    }

    public static boolean flightExistsByInfo(String origin, String destination, String flight_date)
    {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Flight> query = builder.createQuery(Flight.class);
        Root<Flight> root = query.from(Flight.class);
        query.select(root).where(
                builder.and(
                        builder.equal(root.get("origin"), origin),
                        builder.equal(root.get("destination"),destination),
                        builder.equal(root.get("flight_date"),flight_date)));
        List<Flight> flightList = session.createQuery(query).getResultList();

        //if the flightlist is not empty, then the flight already exists - false would return true for this boolean
        //if it is empty, then the flight does not exist - true would return false for this boolean
        return !flightList.isEmpty();
    }

    public static Flight getFlightById(int flight_id)
    {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Flight> query = builder.createQuery(Flight.class);
        Root<Flight> root = query.from(Flight.class);
        query.select(root).where(builder.equal(root.get("flight_id"), flight_id));
        List<Flight> flightList = session.createQuery(query).getResultList();

        return flightList.get(0);
    }

    public static void saveNewFlight(Flight newFlight)
    {
        session.save(newFlight);
    }

    public static int availableSeats(int id)
    {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Flight> query = builder.createQuery(Flight.class);
        Root<Flight> root = query.from(Flight.class);
        query.select(root).where(builder.equal(root.get("flight_id"),id));
        List<Flight> flightList = session.createQuery(query).getResultList();

        int availableSeats = 0;

        for(Flight flight : flightList)
        {
            availableSeats = flight.getNum_seats();
        }

        return availableSeats;
    }

    public static void updateNumSeats(Flight flight)
    {
        //update the num_seats for Flight subtracting the number booked on a ticket
        session.update(flight);
    }

    public static List<Flight> availableFlights() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Flight> query = builder.createQuery(Flight.class);
        Root<Flight> root = query.from(Flight.class);
        query.orderBy(builder.asc(root.get("origin")));
        return session.createQuery(query).getResultList();
    }
    public static void initiateTakeoff(Flight flight)
    {
        flight.setTake_off_status(true);

        session.beginTransaction();
        session.update(flight);
        session.getTransaction().commit();
    }

    public static void deleteFlight(Flight flight)
    {
        session.beginTransaction();
        session.delete(flight);
        session.getTransaction().commit();
    }
}
