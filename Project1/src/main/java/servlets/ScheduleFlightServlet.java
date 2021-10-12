package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ScheduleFlightServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {

        String origin = req.getParameter("origin");
        String destination = req.getParameter("destination");
        String flight_date = req.getParameter("flight_date");

        //check if flight already exists

        System.out.println("Scheduling on" + flight_date + " a flight from " + origin + " to " + destination);

        //insert flight into flights table

        resp.setContentType("text/plain");

        PrintWriter out = resp.getWriter();
        out.println("Flight scheduled successfully!");
    }


}
