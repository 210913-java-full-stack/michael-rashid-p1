package servlets;

import services.LoginService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        System.out.println("Received user: " + username + " | " + password);

        //password validation here
        if(LoginService.checkPassword(username,password))
        {
            //what do we return when we successfully login?

        }
        else
        {
            resp.setContentType("text/plain");

            PrintWriter out = resp.getWriter();
            out.println("Incorrect username and password combination.\n" +
                    "Please go back and try again.");
        }
    }


}
