package repos;

import models.Flight;
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

    //get list of all flights
    public List<Flight> getAllFlights()
    {
        //hibernate logic here
        return null;
    }

    //cancel flight
    public void deleteByID(int id)
    {
        //hibernate logic here
    }

    //get flights by userID
    public List<Flight> getFlightsByUser()
    {
        //hibernate logic here
        return null;
    }
}
