package models;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name="flights")
public class Flight {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private static int flight_id;

    @Column
    static
    String origin;

    @Column
    static
    String destination;

    @Column
    static
    String flight_date;

    @Column
    static
    int num_seats;

    @Column
    static
    boolean take_off_status;

    @OneToMany(mappedBy="flight")
    private static List<Ticket> ticketList = new LinkedList<>();

    public Flight() {

    }

    public Flight(String origin, String destination, String date)
    {
        this.origin = origin;
        this.destination = destination;
        flight_date = date;
        num_seats = 50;
        take_off_status = false;
    }

    public static String getOrigin() {return origin;}

    public static void setOrigin_city(String origin_city) {origin = origin_city;}

    public static String getDestination() {
        return destination;
    }

    public static void setDestination_city(String destination_city) {
        destination = destination_city;
    }

    public static int getNum_seats() {
        return num_seats;
    }

    public static int setNum_seats(int num_seats) { return num_seats;}

    public static void isTake_off_status(boolean take_off_status) {Flight.take_off_status = false;}

    public static void setTake_off_status(boolean take_off_status) {
        take_off_status = false;
    }

    public static String getFlight_date() {
        return flight_date;
    }

    public static void setFlight_date(String flight_date) {Flight.flight_date = flight_date;}

    public static int getFlight_id() {return flight_id;}

    public static void setFlight_id(int flight_id) {Flight.flight_id = flight_id;}

    public static List<Ticket> getTicketList() {
        return ticketList;
    }

    public static void setTicketList() {
        Flight.ticketList = ticketList;
    }
}