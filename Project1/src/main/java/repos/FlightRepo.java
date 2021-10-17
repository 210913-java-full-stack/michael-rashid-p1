package repos;

import models.Flight;
import models.User;
import utils.ConnectionManager;

import java.sql.Connection;
import java.util.List;

public class FlightRepo {
    private Connection conn;

    public FlightRepo()
    {
        this.conn = ConnectionManager.getConnection();
    }

    //save new flight
    public void save(Flight row)
    {
        //hibernate logic here
    }

    //get Flight by ID
    public Flight getFlightById(int id)
    {
        //hibernate logic here
        return null;
    }

    //get Flight by info
    public Flight getFlightByInfo(String origin, String destination, String date)
    {
        //hibernate logic
        //might delete this. might be able to list all the flights in the table including the ID. (should list by origin city)
        return null;
    }

    //get list of all flights
    public List<Flight> getAllFlights()
    {
        //hibernate logic here
        //return a list of all flights that haven't taken off
        return null;
    }

    //cancel flight
    public void deleteByID(int id)
    {
        //hibernate logic here
        //delete flight by the id
    }

    //get flights by userID
    public List<Flight> getFlightsByUser(int userID)
    {
        //hibernate logic here
        //return a list of flights based on the userID or username
        return null;
    }

    //Book tickets by flightID
    public void bookFlightById(User user, int numTickets)
    {
        //hibernate logic here
        //insert into junction table the user and how many tickets they have for a specific flight
        //update how many tickets the flight has available (available tickets - bookedTickets)
    }

}
