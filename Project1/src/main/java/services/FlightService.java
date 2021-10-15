package services;

import models.Flight;
import repos.FlightRepo;

public class FlightService {
    private static FlightRepo flightRepo;

    public static void init()
    {
        if(flightRepo == null)
        {
            flightRepo = new FlightRepo();
        }
    }

    public static boolean flightExists(String origin, String destination, String date)
    {
        return flightRepo.getFlightByInfo(origin, destination, date) != null;
    }

    public static void saveNewFlight(Flight newFlight)
    {
        flightRepo.save(newFlight);
    }

//    public static int availableSeats(Flight flight)
//    {
//
//    }
}
