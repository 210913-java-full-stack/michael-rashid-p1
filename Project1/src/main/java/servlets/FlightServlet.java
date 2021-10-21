package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.DeleteFlight;
import models.DeleteTicket;
import models.Flight;
import models.Ticket;
import services.FlightService;
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

public class FlightServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {

        InputStream requestBody = req.getInputStream();
        Scanner sc = new Scanner(requestBody, StandardCharsets.UTF_8.name());
        String jsonText = sc.useDelimiter("\\A").next();
        ObjectMapper objectMapper = new ObjectMapper();
        Flight payload = objectMapper.readValue(jsonText,Flight.class);

        //check if flight already exists
        if(FlightService.flightExistsByInfo(payload.getOrigin(),payload.getDestination(),payload.getFlight_date()))
        {
            resp.setContentType("text/plain");
            resp.setStatus(406);
            resp.getWriter().println("Flight already exists!");
        }
        else
        {
            resp.setStatus(200);
            FlightService.saveNewFlight(payload);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        InputStream requestBody = req.getInputStream();
        Scanner sc = new Scanner(requestBody, StandardCharsets.UTF_8.name());
        String jsonText = sc.useDelimiter("\\A").next();
        ObjectMapper objectMapper = new ObjectMapper();
        DeleteFlight payload = objectMapper.readValue(jsonText,DeleteFlight.class);

        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();
        //get flight from flight_id
        Flight currentFlight = FlightService.getFlightById(payload.getFlight_id());

        if(currentFlight != null)
        {
            //flight exists so delete the flight and the tickets associated with it (cascade delete)
            resp.setStatus(200);
            FlightService.deleteFlight(currentFlight);
        }
        else
        {
            resp.setStatus(406);
            out.write("Flight not found, could not delete.");
        }
    }
}
