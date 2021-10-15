package servlets;

import models.Flight;
import services.FlightService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ScheduleFlightServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {

        String origin = req.getParameter("origin");
        String destination = req.getParameter("destination");
        String flight_date = req.getParameter("flight_date");

        //check if flight already exists
        if(FlightService.flightExists(origin, destination, flight_date))
        {
            resp.setContentType("text/plain");
            resp.getWriter().println("Flight already exists from " + origin + " " +
                    "to " + destination + " on " + flight_date + ". Please try again.");
        }
        else
        {
            Flight flight = new Flight(origin,destination,50,false);
            System.out.println("Scheduling on " + flight_date + " a flight from " + origin + " to " + destination);
            FlightService.saveNewFlight(flight);

            resp.setContentType("text/plain");

            PrintWriter out = resp.getWriter();
            out.println("Flight scheduled successfully!");
        }
    }


}
