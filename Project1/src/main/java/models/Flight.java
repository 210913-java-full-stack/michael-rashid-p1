package models;

public class Flight {

    String origin_city;
    String destination_city;
    int num_seats;
    boolean take_off_status;
    String flight_date;


    public Flight(String origin_city, String destination_city, int num_seats, boolean take_off_status, String flight_date) {
        this.origin_city = origin_city;
        this.destination_city = destination_city;
        this.num_seats = num_seats;
        this.take_off_status = take_off_status;
        this.flight_date = flight_date;
    }

    public String getOrigin_city() {
        return origin_city;
    }

    public void setOrigin_city(String origin_city) {
        this.origin_city = origin_city;
    }

    public String getDestination_city() {
        return destination_city;
    }

    public void setDestination_city(String destination_city) {
        this.destination_city = destination_city;
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