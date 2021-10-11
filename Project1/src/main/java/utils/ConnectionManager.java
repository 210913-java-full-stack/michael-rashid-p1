package utils;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager
{
    private static Connection conn;

    private ConnectionManager()
    {

    }

    public static Connection getConnection() {
        if(conn == null) {
            try {
                Properties props = new Properties();
                ClassLoader cl = Thread.currentThread().getContextClassLoader();
                InputStream fileIn = cl.getResourceAsStream("connection.properties");
                props.load(fileIn);
                Class.forName("org.mariadb.jdbc.Driver");

                //"jdbc:mariadb://hostname:port/databaseName?user=username&password=password"
                String connString = "jdbc:mariadb://" +
                        props.getProperty("hostname") + ":" +
                        props.getProperty("port") + "/" +
                        props.getProperty("databaseName") + "?user=" +
                        props.getProperty("user") + "&password=" +
                        props.getProperty("password");

                System.out.println("Connected to data source!");
                conn = DriverManager.getConnection(connString);
            } catch(SQLException | IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

}
