package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.*;
import services.FlightService;
import services.TicketService;
import utils.FileLogger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

public class FlightServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        try {
            List<Flight> flightList = FlightService.availableFlights();
            ObjectMapper mapper = new ObjectMapper();
            resp.getWriter().write(mapper.writeValueAsString(flightList));
            resp.setContentType("application/json");
            resp.setStatus(200);
        }
        catch(IOException e)
        {
            FileLogger.getFileLogger().console().threshold(0).writeLog("Can't get Writer object for response.", 0);
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    {
        try {
            InputStream requestBody = req.getInputStream();
            Scanner sc = new Scanner(requestBody, StandardCharsets.UTF_8.name());
            String jsonText = sc.useDelimiter("\\A").next();
            ObjectMapper objectMapper = new ObjectMapper();
            Flight payload = objectMapper.readValue(jsonText, Flight.class);

            //check if flight already exists
            if (FlightService.flightExistsByInfo(payload.getOrigin(), payload.getDestination(), payload.getFlight_date())) {
                resp.setContentType("text/plain");
                resp.setStatus(406);
                resp.getWriter().println("Flight already exists!");
            } else {
                resp.setStatus(200);
                FlightService.saveNewFlight(payload);
            }
        }
        catch(IOException e)
        {
            FileLogger.getFileLogger().console().threshold(0).writeLog("No request body to read from.", 0);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
    {
        InputStream requestBody = null;
        try {
            requestBody = req.getInputStream();
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
                if(currentFlight.isTake_off_status())
                {
                    resp.setStatus(406);
                    out.println("Flight has already taken off, it can't be cancelled.");
                }
                else
                {
                    resp.setStatus(200);
                    FlightService.deleteFlight(currentFlight);
                }
            }
            else
            {
                resp.setStatus(406);
                out.write("Flight not found, could not delete.");
            }

        } catch (IOException e) {
            FileLogger.getFileLogger().console().threshold(0).writeLog("No request body to read from.", 0);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp){
        InputStream requestBody = null;
        try {
            requestBody = req.getInputStream();
            Scanner sc = new Scanner(requestBody, StandardCharsets.UTF_8.name());
            String jsonText = sc.useDelimiter("\\A").next();
            ObjectMapper objectMapper = new ObjectMapper();
            FlightID payload = objectMapper.readValue(jsonText, FlightID.class);

            resp.setContentType("text/plain");
            PrintWriter out = resp.getWriter();
            //get flight from flight_id, make sure it exists.
            Flight currentFlight = FlightService.getFlightById(payload.getFlight_id());

            if(currentFlight != null)
            {
                FlightService.initiateTakeoff(currentFlight);
                resp.setStatus(200);
            }
            else
            {
                resp.setStatus(406);
                out.write("Flight not found, could not initiate takeoff.");
            }
        } catch (IOException e) {
            FileLogger.getFileLogger().console().threshold(0).writeLog("No request body to read from.", 0);
        }
    }
}
