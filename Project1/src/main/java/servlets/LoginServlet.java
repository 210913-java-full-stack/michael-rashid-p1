package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Login;
import models.User;
import services.UserService;
import utils.FileLogger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    {
        try {
            //arguably, this should be a get request I think. but I'm taking in the username and password
            //and putting it into my login model
            InputStream requestBody = req.getInputStream();
            Scanner sc = new Scanner(requestBody, StandardCharsets.UTF_8.name());
            String jsonText = sc.useDelimiter("\\A").next();
            ObjectMapper objectMapper = new ObjectMapper();
            Login payload = objectMapper.readValue(jsonText, Login.class);

            //password validation here
            User loggedIn = UserService.checkPassword(payload.getUsername(), payload.getPassword());
            //if checkPassword returned a user, then we successfully logged in.
            if (loggedIn != null) {
                resp.setStatus(200);
                System.out.println("Successful login!");
                //send back the object as json so the front end can pull the role out of it and redirect the user
                resp.setContentType("application/json");
                ObjectMapper mapper = new ObjectMapper();
                resp.getWriter().write(mapper.writeValueAsString(loggedIn));
            } else {
                //response setup
                resp.setContentType("text/plain");
                PrintWriter out = resp.getWriter();

                resp.setStatus(401);
                //401 unauthorized response

                out.println("Incorrect username and password combination.\n" +
                        "Please go back and try again.");
            }
        }
        catch(IOException e)
        {
            FileLogger.getFileLogger().console().threshold(0).writeLog("No request body to get response from.", 0);
        }
    }
}
