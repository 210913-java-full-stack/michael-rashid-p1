package services;

import repos.UserRepo;

public class LoginService {
    private static UserRepo userRepo;

    public static void init()
    {
        if(userRepo == null)
        {
            userRepo = new UserRepo();
        }
    }

    public static boolean checkPassword(String username, String password)
    {
        return userRepo.checkPassword(username, password);
    }
}
