package servlets;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RegisterServlet extends HttpServlet {
    //this is going to be our test servlet
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String fName = req.getParameter("fName");
        String lName = req.getParameter("lName");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        System.out.println("Received user: " + fName + " | " + lName + " | " +
                username + " | " + password);

        resp.setContentType("text/plain");

        PrintWriter out = resp.getWriter();
        out.println("Received user information!");
    }
}
