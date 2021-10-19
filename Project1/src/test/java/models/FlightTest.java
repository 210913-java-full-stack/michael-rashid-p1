package models;

import junit.framework.TestCase;
import org.junit.jupiter.api.Assertions;


public class FlightTest extends TestCase {

    public void testGetOrigin() {
        Flight flight = new Flight();
        Flight.setOrigin_city("Albuquerque");
        Assertions.assertFalse(Flight.getOrigin().isEmpty());
    }

    public void testSetOrigin_city() {
        Flight flight = new Flight();
        Flight.setOrigin_city("Albuquerque");
        Assertions.assertFalse(Flight.getOrigin().isEmpty());
    }

    public void testGetDestination() {
        Flight flight = new Flight();
        Flight.setDestination_city(("Albuquerque"));
        Assertions.assertFalse(Flight.getDestination().isEmpty());
    }

    public void testSetDestination_city() {
        Flight flight = new Flight();
        Flight.setDestination_city(("Albuquerque"));
        Assertions.assertFalse(Flight.getDestination().isEmpty());
    }

    public void testGetNum_seats() {
        Flight flight = new Flight();
        Flight.setNum_seats(25);
        Assertions.assertFalse(Flight.getNum_seats()!=0);
    }

    public void testSetNum_seats() {
        Flight flight = new Flight();
        Flight.setNum_seats(25);
        Assertions.assertFalse(Flight.getNum_seats()!=0);
    }

    public void testIsTake_off_status() {
        Flight flight = new Flight();
        Flight.setTake_off_status(false);
        Assertions.assertFalse(Flight.take_off_status==true);
    }

    public void testSetTake_off_status() {
        Flight flight = new Flight();
        Flight.setTake_off_status(false);
        Assertions.assertFalse(Flight.take_off_status==true);
    }

    public void testGetFlight_date() {
        Flight flight = new Flight();
        Flight.setFlight_date("2021-10-18");
        Assertions.assertFalse(Flight.getFlight_date().isEmpty());
    }

    public void testSetFlight_date() {
        Flight flight = new Flight();
        Flight.setFlight_date("2021-10-18");
        Assertions.assertFalse(Flight.getFlight_date().isEmpty());
    }

    public void testGetFlight_id() {
        Flight flight = new Flight();
        Flight.setFlight_id(101);
        Assertions.assertFalse(Flight.getFlight_id()==0);
    }

    public void testSetFlight_id() {
        Flight flight = new Flight();
        Flight.setFlight_id(101);
        Assertions.assertFalse(Flight.getFlight_id()==0);
    }

    public void testGetTicketList() {
        Flight flight = new Flight();
        Flight.getTicketList();
        Assertions.assertTrue(Flight.getTicketList().isEmpty());
    }

//    public void testSetTicketList() {
//        Flight flight = new Flight();
//        Flight.setTicketList()=12;
//        Assertions.assertFalse(Flight.getTicketList().isEmpty());
//    }
}