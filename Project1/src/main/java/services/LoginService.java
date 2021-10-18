package services;

import models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import repos.UserRepo;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class LoginService {
    private static SessionFactory sessionFactory = ServiceHolder.getSessionFactory();
    private static Session session = ServiceHolder.getSession();

    public static boolean checkPassword(String username, String password)
    {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root).where(
                builder.and(
                        builder.equal(root.get("username"), username),
                        builder.equal(root.get("password"), password)));

        List<User> userList = session.createQuery(query).getResultList();

        //instead of returning bool at this point, return a new user object that you pull from this list
        //it should only have 1 result, provided username is unique, so pull item at index 0,
        //and then pass it back. Do a check in the servlet if the user object == null;

        return userList.isEmpty();
    }
}
