package servlets;

import models.User;
import services.RegisterService;
import utils.NameValidation;

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
        if(NameValidation.isValidString(fName) && NameValidation.isValidString(lName)) {
            if (RegisterService.uniqueUsername(username))
            {
                User newUser = new User(fName,lName,username,password,role);
                RegisterService.saveNewUser(newUser);
            }
            else
            {
                //throw custom exception to log?
                resp.setContentType("text/plain");

                PrintWriter out = resp.getWriter();
                out.println("User not registered. Username must be unique. Please refresh and try again.");
            }
        }
        else
        {
            //throw custom exception to log to a file?
            resp.setContentType("text/plain");

            PrintWriter out = resp.getWriter();
            out.println("User not registered. First and/or Last name contained invalid characters.\n" +
                    "Please go back and try again.");
        }

        //insert newUser's info into the user_info table.
        //RegisterService.saveNewUser(newUser);

    }
}
