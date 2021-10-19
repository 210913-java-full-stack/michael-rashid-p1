package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.*;
import services.FlightService;
import services.LoginService;
import services.TicketService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class BookFlightServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        InputStream requestBody = req.getInputStream();
        Scanner sc = new Scanner(requestBody, StandardCharsets.UTF_8.name());
        String jsonText = sc.useDelimiter("\\A").next();
        ObjectMapper objectMapper = new ObjectMapper();
        Booking payload = objectMapper.readValue(jsonText,Booking.class);

        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();

        //get the user from the username, make sure they exist
        User currentUser = LoginService.getUserByUsername(payload.getUsername());
        if(currentUser == null)
        {
            //throw exception?
            resp.setStatus(406);
            out.println("Username does not exist.");
        }
        else{
            System.out.println("Checking userid: " + currentUser.getUser_id());
            //checking if flight they're trying to book exists
            Flight currentFlight = FlightService.flightExistsById(payload.getFlight_id());
            if(currentFlight != null)
            {
                //checking if there's enough available seats
                if(FlightService.availableSeats(payload.getFlight_id()) >= payload.getNum_tickets())
                {
                    //then they can book the flight. add ticket
                    Ticket newTicket = new Ticket(payload.getNum_tickets(),false,currentFlight,currentUser);
                    TicketService.saveNewFlight(newTicket);
                    FlightService.updateNumSeats(payload.getFlight_id(),payload.getNum_tickets());

                    resp.setStatus(200);
                    out.println("Flight booked successfully for" + currentUser.getfName() + "!");
                }
                else
                {
                    //throw exception?
                    resp.setStatus(406);
                    out.println("There are not enough available seats! Please try again.");
                }
            }
            else
            {
                //throw exception?
                resp.setStatus(406);
                out.println("That flight_id does not exist. Please try again.");
            }
        }
    }


}
