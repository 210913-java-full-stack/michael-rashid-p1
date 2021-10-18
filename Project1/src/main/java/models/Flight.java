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
    private int flight_id;

    @Column
    String origin;

    @Column
    String destination;

    @Column
    String flight_date;

    @Column
    int num_seats;

    @Column
    boolean take_off_status;

    @OneToMany(mappedBy="flight")
    private List<Ticket> ticketList = new LinkedList<>();

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

    public String getOrigin() {
        return origin;
    }

    public void setOrigin_city(String origin_city) {
        this.origin = origin_city;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination_city(String destination_city) {
        this.destination = destination_city;
    }

    public int getNum_seats() {
        return num_seats;
    }

    public void setNum_seats(int num_seats) {
        this.num_seats = num_seats;
    }

    public boolean isTake_off_status() {
        return take_off_status;
    }

    public void setTake_off_status(boolean take_off_status) {
        this.take_off_status = take_off_status;
    }

    public String getFlight_date() {
        return flight_date;
    }

    public void setFlight_date(String flight_date) {
        this.flight_date = flight_date;
    }

    public int getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(int flight_id) {
        this.flight_id = flight_id;
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }
}