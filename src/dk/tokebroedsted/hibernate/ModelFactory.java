package dk.tokebroedsted.hibernate;

import dk.tokebroedsted.hibernate.tables.Feed;
import dk.tokebroedsted.hibernate.tables.User;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: toke
 * Date: 18-07-12
 * Time: 22:53
 * To change this template use File | Settings | File Templates.
 */
public class ModelFactory {

    public static List<Feed> getFeeds(User user) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        ArrayList<Feed> feeds = new ArrayList<Feed>();
        session.beginTransaction();
        Query query = session.createQuery("FROM Feed WHERE owner = :owner");
        query.setParameter("owner", user);

        java.util.List allFeeds = query.list();
        for (int i = 0; i < allFeeds.size(); i++) {
//            dk.tokebroedsted.hibernate.tables.User user = (dk.tokebroedsted.hibernate.tables.User) allUsers.get(i);
//            logger.info("We found " + user.getUsername() + " users with our query.");
//            User mUser = new User(user.getId(), user.getLoginname(), user.getUsername(), user.getPassword(), user.getEmail());
//            users.add(mUser);
//        }
//        session.getTransaction().commit();
        }
        return null;
    }
}
