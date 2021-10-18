package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class RegisterServlet extends HttpServlet {
    //this is going to be our test servlet
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        InputStream requestBody = req.getInputStream();
        Scanner sc = new Scanner(requestBody, StandardCharsets.UTF_8.name());
        String jsonText = sc.useDelimiter("\\A").next();
        ObjectMapper objectMapper = new ObjectMapper();
        User payload = objectMapper.readValue(jsonText,User.class);
        if(NameValidation.isValidString(payload.getfName()) && NameValidation.isValidString(payload.getlName()))
        {
            if(RegisterService.uniqueUsername(payload.getUsername()))
            {
                RegisterService.saveNewUser(payload);
            }
            else
            {
                System.out.println("Username already taken.");

                //throw custom exception?
                resp.setContentType("text/plain");
                resp.setStatus(406);


                PrintWriter out = resp.getWriter();
                out.println("User not registered. Username must be unique. Please go back and try again.");
            }
        }
        else
        {
            System.out.println("Invalid first or last name.");

            //throw custom exception to log to a file?
            resp.setContentType("text/plain");

            PrintWriter out = resp.getWriter();
            out.println("User not registered. First and/or Last name contained invalid characters.\n" +
                    "Please go back and try again.");
        }
    }
}
