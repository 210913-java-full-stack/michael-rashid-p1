package services;

import models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.List;

public class LoginService {
    private static SessionFactory sessionFactory = ServiceHolder.getSessionFactory();
    private static Session session = ServiceHolder.getSession();

    public static User checkPassword(String username, String password)
    {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root).where(
                builder.and(
                        builder.equal(root.get("username"), username),
                        builder.equal(root.get("password"), password)));

        List<User> userList = session.createQuery(query).getResultList();

        if(!userList.isEmpty())
        {
            User newUser = new User(userList.get(0).getUser_id(),userList.get(0).getfName(),
                    userList.get(0).getlName(),
                    userList.get(0).getUsername(),
                    userList.get(0).getPassword(),
                    userList.get(0).getRole());

            return newUser;
        }

        return null;
    }

    public static User getUserByUsername(String username)
    {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root).where(builder.equal(root.get("username"), username));
        List<User> userList = session.createQuery(query).getResultList();

        if(!userList.isEmpty())
        {
            return userList.get(0);
        }
        return null;
    }
}
