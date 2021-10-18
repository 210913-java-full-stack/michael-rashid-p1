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
        query.select(root).where(builder.equal(root.get("username"), username), builder.equal(root.get("password"), password));
        List<User> userList = session.createQuery(query).getResultList();

        return userList.isEmpty();
    }
}
