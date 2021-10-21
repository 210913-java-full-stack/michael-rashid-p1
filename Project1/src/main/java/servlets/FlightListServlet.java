package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Flight;
import services.FlightService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class FlightListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Flight> flightList = FlightService.availableFlights();
        ObjectMapper mapper = new ObjectMapper();
        resp.getWriter().write(mapper.writeValueAsString(flightList));
        resp.setContentType("application/json");
        resp.setStatus(200);
    }
}
