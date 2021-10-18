package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Flight;
import models.User;
import services.FlightService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ScheduleFlightServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {

        InputStream requestBody = req.getInputStream();
        Scanner sc = new Scanner(requestBody, StandardCharsets.UTF_8.name());
        String jsonText = sc.useDelimiter("\\A").next();
        ObjectMapper objectMapper = new ObjectMapper();
        Flight payload = objectMapper.readValue(jsonText,Flight.class);

        //check if flight already exists
        if(FlightService.flightExists(payload.getOrigin(),payload.getDestination(),payload.getFlight_date()))
        {
            resp.setContentType("text/plain");
            resp.getWriter().println("Flight already exists!");
        }
        else
        {
            System.out.println("Scheduling new flight | " + payload.getOrigin());
            FlightService.saveNewFlight(payload);
        }
    }


}
