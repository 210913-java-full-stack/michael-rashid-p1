package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Flight;
import models.Login;
import models.Ticket;
import models.User;
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
import java.util.List;
import java.util.Scanner;

public class UserFlightListServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        InputStream requestBody = req.getInputStream();
        Scanner sc = new Scanner(requestBody, StandardCharsets.UTF_8.name());
        String jsonText = sc.useDelimiter("\\A").next();
        ObjectMapper objectMapper = new ObjectMapper();
        Login payload = objectMapper.readValue(jsonText,Login.class);

        //get user ID from username
        User currentUser = LoginService.getUserByUsername(payload.getUsername());
        if(currentUser != null)
        {
            List<Ticket> ticketList = TicketService.getTicketsByUser(currentUser.getUser_id());
            ObjectMapper mapper = new ObjectMapper();
            resp.getWriter().write(mapper.writeValueAsString(ticketList));
            resp.setContentType("application/json");
            resp.setStatus(200);
        }
        else
        {
            resp.setStatus(406);
            resp.setContentType("text/plain");
           PrintWriter out = resp.getWriter();

           out.write("Username does not exist!");
        }
    }
}
