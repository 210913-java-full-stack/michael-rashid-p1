package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Login;
import models.Ticket;
import models.User;
import services.UserService;
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

public class UserFlightListServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        try {
            //this should probably be a get, but I'm getting a username from the user to display their flight list
            InputStream requestBody = req.getInputStream();
            Scanner sc = new Scanner(requestBody, StandardCharsets.UTF_8.name());
            String jsonText = sc.useDelimiter("\\A").next();
            ObjectMapper objectMapper = new ObjectMapper();
            Login payload = objectMapper.readValue(jsonText, Login.class);

            //get user from username
            User currentUser = UserService.getUserByUsername(payload.getUsername());

            //if that user exists, get their list of tickets
            if (currentUser != null) {
                List<Ticket> ticketList = TicketService.getTicketsByUser(currentUser);
                ObjectMapper mapper = new ObjectMapper();
                resp.getWriter().write(mapper.writeValueAsString(ticketList));
                resp.setContentType("application/json");
                resp.setStatus(200);
            } else {
                resp.setStatus(406);
                resp.setContentType("text/plain");
                PrintWriter out = resp.getWriter();

                out.write("Username does not exist!");
            }
        }
        catch(IOException e)
        {
            FileLogger.getFileLogger().console().threshold(0).writeLog("No input body to read from.", 0);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        try {
            //get the list of all tickets that exist
            List<Ticket> ticketList = TicketService.getTicketList();
            ObjectMapper mapper = new ObjectMapper();
            resp.getWriter().write(mapper.writeValueAsString(ticketList));
            resp.setContentType("application/json");
            resp.setStatus(200);
        }
        catch(IOException e)
        {
            FileLogger.getFileLogger().console().threshold(0).writeLog("Unable to get Writer for response body.", 0);
        }
    }
}
