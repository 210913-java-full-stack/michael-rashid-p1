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
            //grab the available flights list from the flight service class and then return it to the application as a json string
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
            //receive new flight information as and map it to a flight object using the flight class
            InputStream requestBody = req.getInputStream();
            Scanner sc = new Scanner(requestBody, StandardCharsets.UTF_8.name());
            String jsonText = sc.useDelimiter("\\A").next();
            ObjectMapper objectMapper = new ObjectMapper();
            Flight payload = objectMapper.readValue(jsonText, Flight.class);

            //check if flight already exists, if it does let the user know
            if (FlightService.flightExistsByInfo(payload.getOrigin(), payload.getDestination(), payload.getFlight_date())) {
                resp.setContentType("text/plain");
                resp.setStatus(406);
                resp.getWriter().println("Flight already exists!");
            } else {
                //if the flight does not exist, save it.
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
        //receive the object in the request body and map it to my flightId class
        InputStream requestBody = null;
        try {
            requestBody = req.getInputStream();
            Scanner sc = new Scanner(requestBody, StandardCharsets.UTF_8.name());
            String jsonText = sc.useDelimiter("\\A").next();
            ObjectMapper objectMapper = new ObjectMapper();
            FlightID payload = objectMapper.readValue(jsonText,FlightID.class);

            //there are multiple times I use this, so I define it out here so I don't have to define them multiple times
            resp.setContentType("text/plain");
            PrintWriter out = resp.getWriter();


            //get flight from flight_id
            Flight currentFlight = FlightService.getFlightById(payload.getFlight_id());

            //if currentFlight does not exist. you can't delete it
            if(currentFlight != null)
            {
                //if the flight is already taken off, you can't cancel it.
                if(currentFlight.isTake_off_status())
                {
                    resp.setStatus(406);
                    out.println("Flight has already taken off, it can't be cancelled.");
                }
                else
                {
                    //if it exists and is not taken off, then delete it
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
            //grab the flight id and put it into the flightID object
            requestBody = req.getInputStream();
            Scanner sc = new Scanner(requestBody, StandardCharsets.UTF_8.name());
            String jsonText = sc.useDelimiter("\\A").next();
            ObjectMapper objectMapper = new ObjectMapper();
            FlightID payload = objectMapper.readValue(jsonText, FlightID.class);

            resp.setContentType("text/plain");
            PrintWriter out = resp.getWriter();
            //get flight from flight_id
            Flight currentFlight = FlightService.getFlightById(payload.getFlight_id());

            //if the flight exists, we can initiate takeoff
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
