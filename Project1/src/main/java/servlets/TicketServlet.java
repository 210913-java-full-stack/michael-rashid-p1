package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.*;
import services.FlightService;
import services.LoginService;
import services.TicketService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class TicketServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException
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
            //checking if flight they're trying to book exists
            Flight currentFlight = FlightService.getFlightById(payload.getFlight_id());
            if(currentFlight != null)
            {
                //checking if there's enough available seats
                if(FlightService.availableSeats(payload.getFlight_id()) >= payload.getNum_tickets())
                {
                    //then they can book the flight. add ticket
                    Ticket newTicket = new Ticket(payload.getNum_tickets(),false,currentFlight,currentUser);
                    TicketService.saveNewFlight(newTicket);
                    currentFlight.setNum_seats(currentFlight.getNum_seats()-payload.getNum_tickets());
                    FlightService.updateNumSeats(currentFlight);

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

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        InputStream requestBody = req.getInputStream();
        Scanner sc = new Scanner(requestBody, StandardCharsets.UTF_8.name());
        String jsonText = sc.useDelimiter("\\A").next();
        ObjectMapper objectMapper = new ObjectMapper();
        DeleteTicket payload = objectMapper.readValue(jsonText,DeleteTicket.class);

        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();
        //get ticket from ticket_id
        Ticket currentTicket = TicketService.getTicketById(payload.getTicket_id());

        if(currentTicket != null)
        {
            //ticket exists so delete the ticket
            TicketService.deleteTicket(currentTicket);
            resp.setStatus(200);
        }
        else
        {
            resp.setStatus(406);
            out.write("Tickets not found, could not delete.");
        }
    }
}
