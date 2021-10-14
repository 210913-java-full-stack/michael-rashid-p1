package repos;

import models.User;
import utils.ConnectionManager;

import java.sql.Connection;

public class UserRepo {

    private Connection conn;

    public UserRepo()
    {
        this.conn = ConnectionManager.getConnection();
    }

    //save new user
    public void save(User row)
    {
        //hibernate logic here
    }

    public User getUserByID(int id)
    {
        //hibernate logic here
        return null;
    }

    public boolean checkPassword(String username, String password)
    {
        //hibernate logic here. grabbing user by username and checking if the password matches.
        //Enforcing that usernames are unique is a requirement.
        return false;
    }

    public boolean getUserByUsername(String username){
        //hibernate logic. if this comes back with a resultset then it exists already
        //and is not a valid username.
        return false;
    }
}
