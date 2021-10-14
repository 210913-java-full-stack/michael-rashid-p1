package services;

import models.User;
import repos.UserRepo;

import java.sql.SQLException;

public class RegisterService {
    private static UserRepo userRepo;

    public static void init()
    {
        if(userRepo == null)
        {
            userRepo = new UserRepo();
        }
    }

    public static boolean uniqueUsername(String username)
    {
        return !userRepo.getUserByUsername(username);
    }

    public static void saveNewUser(User newUser)
    {
        userRepo.save(newUser);
    }

}
