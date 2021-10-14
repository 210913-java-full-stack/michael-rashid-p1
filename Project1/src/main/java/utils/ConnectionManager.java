package utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {
    private static Connection conn;

    private ConnectionManager()
    {

    }

    public static Connection getConnection(){
        if(conn == null)
        {
            try
            {
                Properties props = new Properties();
                ClassLoader c1 = Thread.currentThread().getContextClassLoader();
                InputStream fileIn = c1.getResourceAsStream("connections.properties");
                props.load(fileIn);
                Class.forName("org.mariadb.jdbc.Driver");

                //"jdbc:mariadb://hostname:port/databaseName?user=username&password=password"
                String connectionString = "jdbc:mariadb://"
                        + props.getProperty("hostname")
                        + ":" + props.getProperty("port")
                        + "/" + props.getProperty("databaseName")
                        + "?user=" + props.getProperty("user")
                        + "&password=" + props.getProperty("password");

                //getting connection from connection properties above
                conn = DriverManager.getConnection(connectionString);
                System.out.println("Connection successful!");
            }
            catch (SQLException | IOException | ClassNotFoundException e)
            {
                e.printStackTrace();
            }
        }
        return conn;
    }
}
