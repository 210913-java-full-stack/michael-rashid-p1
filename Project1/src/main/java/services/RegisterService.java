package services;

import models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import repos.UserRepo;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.SQLException;
import java.util.List;

public class RegisterService {
    private static SessionFactory sessionFactory = ServiceHolder.getSessionFactory();
    private static Session session = ServiceHolder.getSession();

    public static boolean uniqueUsername(String username)
    {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root).where(builder.equal(root.get("username"), username));
        List<User> userList = session.createQuery(query).getResultList();

        return userList.isEmpty();
    }

    public static void saveNewUser(User newUser)
    {
        session.save(newUser);
    }

    public static User getUserById(int id)
    {
        return session.get(User.class, id);
    }

}
