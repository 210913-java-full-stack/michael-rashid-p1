package models;

import javax.persistence.*;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ticket_id;

    @Column
    private int num_tickets;

    @Column
    private boolean check_in_status;

    //there are many tickets per one flight
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private Flight flight;

    //there are many tickets per one user
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable=false)
    private User user;

    public Ticket( int num_tickets, boolean check_in_status) {
        this.num_tickets = num_tickets;
        this.check_in_status = check_in_status;
    }

    public Ticket()
    {

    }

    public int getNum_tickets() {
        return num_tickets;
    }

    public void setNum_tickets(int num_tickets) {
        this.num_tickets = num_tickets;
    }

    public boolean isCheck_in_status() {
        return check_in_status;
    }

    public void setCheck_in_status(boolean check_in_status) {
        this.check_in_status = check_in_status;
    }

    public int getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(int ticket_id) {
        this.ticket_id = ticket_id;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
