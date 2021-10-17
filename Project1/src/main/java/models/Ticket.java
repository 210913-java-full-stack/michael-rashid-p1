package models;

public class Ticket {
    int user_id;
    int flight_id;
    int num_tickets;
    boolean check_in_status;

    public Ticket(int user_id, int flight_id, int num_tickets, boolean check_in_status) {
        this.user_id = user_id;
        this.flight_id = flight_id;
        this.num_tickets = num_tickets;
        this.check_in_status = check_in_status;
    }

    public Ticket()
    {

    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(int flight_id) {
        this.flight_id = flight_id;
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
}
