package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class BookFlightServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {

        String origin = req.getParameter("origin");
        String destination = req.getParameter("destination");
        int num_tickets = Integer.parseInt(req.getParameter("num_tickets"));

        //insert ticket amount checking here.
        System.out.println("Booking " + num_tickets + " ticket(s) from " + origin + " to " + destination);

        //insert tickets into user_flights junction table, origin+destination will get us the-
        //flight_id and we have the user_id saved

        resp.setContentType("text/plain");

        PrintWriter out = resp.getWriter();
        out.println("Flight booked successfully!");
    }




}
