package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.User;
import services.UserService;
import utils.FileLogger;
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
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    {
        try {
            //taking in new user information and mapping it to my user class
            InputStream requestBody = req.getInputStream();
            Scanner sc = new Scanner(requestBody, StandardCharsets.UTF_8.name());
            String jsonText = sc.useDelimiter("\\A").next();
            ObjectMapper objectMapper = new ObjectMapper();
            User payload = objectMapper.readValue(jsonText, User.class);

            //check to make sure first and last name don't contain weird characters
            if (NameValidation.isValidString(payload.getfName()) && NameValidation.isValidString(payload.getlName())) {
                //checks to see if username is unique
                if (UserService.uniqueUsername(payload.getUsername())) {
                    //if it passes both checks, then save the user
                    UserService.saveNewUser(payload);
                    resp.setStatus(200);
                } else {
                    System.out.println("Username already taken.");

                    resp.setContentType("text/plain");
                    resp.setStatus(406);

                    PrintWriter out = resp.getWriter();
                    out.println("User not registered. Username must be unique. Please go back and try again.");
                }
            } else {
                System.out.println("Invalid first or last name.");

                resp.setContentType("text/plain");
                resp.setStatus(406);

                PrintWriter out = resp.getWriter();
                out.println("User not registered. First and/or Last name contained invalid characters.\n" +
                        "Please go back and try again.");
            }
        }
        catch(IOException e)
        {
            FileLogger.getFileLogger().console().threshold(0).writeLog("No input body to read from.", 0);
        }
    }
}
