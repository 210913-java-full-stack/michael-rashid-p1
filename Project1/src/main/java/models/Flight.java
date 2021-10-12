package models;

public class Flight {

    String origin_city;
    String origin_state;
    String destination_city;
    String destination_state;
    int num_seats;
    boolean take_off_status;
    String flight_date;


    public Flight(String origin_city, String origin_state, String destination_city, String destination_state, int num_seats, boolean take_off_status) {
        this.origin_city = origin_city;
        this.origin_state = origin_state;
        this.destination_city = destination_city;
        this.destination_state = destination_state;
        this.num_seats = num_seats;
        this.take_off_status = take_off_status;
    }

    public String getOrigin_city() {
        return origin_city;
    }

    public void setOrigin_city(String origin_city) {
        this.origin_city = origin_city;
    }

    public String getOrigin_state() {
        return origin_state;
    }

    public void setOrigin_state(String origin_state) {
        this.origin_state = origin_state;
    }

    public String getDestination_city() {
        return destination_city;
    }

    public void setDestination_city(String destination_city) {
        this.destination_city = destination_city;
    }

    public String getDestination_state() {
        return destination_state;
    }

    public void setDestination_state(String destination_state) {
        this.destination_state = destination_state;
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
}