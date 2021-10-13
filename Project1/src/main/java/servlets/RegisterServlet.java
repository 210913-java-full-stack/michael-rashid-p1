package servlets;

import models.User;

import javax.servlet.RequestDispatcher;
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
        String role = req.getParameter("role");


        System.out.println("Received user: " + fName + " | " + lName + " | " +
                username + " | " + password + " | " + role);

        //name validation here

        User newUser = new User(fName,lName,username,password,role);


        // Attempts to redirect to next web page, getting 404 error currently
        resp.sendRedirect("HTML/Login.html");

        //insert newUser's info into the user_info table.

//        // get response writer
//        PrintWriter writer = resp.getWriter();
//
//        // build HTML code
//        String htmlRespone = "<html>";
//        htmlRespone += "<h2>Your username is: " + username + "<br/>";
//        htmlRespone += "Your password is: " + password + "</h2>";
//        htmlRespone += "<a href=\"HTML/BookFlights.html\"> Click me to Book a Flight </a>";
//        htmlRespone += "</html>";
//
//        // return response
//        writer.println(htmlRespone);




//        resp.setContentType("text/plain");
//
//        PrintWriter out = resp.getWriter();
//        out.println("Received user information!");

        //RequestDispatcher view = req.getRequestDispatcher("HTML/Login.html");
        //view.forward(req,resp);
    }
}
