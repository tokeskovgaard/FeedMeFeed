package dk.tokebroedsted.hibernate;

import dk.tokebroedsted.hibernate.tables.Feed;
import dk.tokebroedsted.hibernate.tables.FeedItem;
import dk.tokebroedsted.hibernate.tables.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
        Session session = getSession();

        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM Feed WHERE owner = :owner");
        query.setParameter("owner", user);

        List<Feed> allFeeds = query.list();
        transaction.commit();
        return allFeeds;
    }

    public static User getUser(String username) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM User WHERE username = :username");
        query.setParameter("username", username);
        User user = (User) query.uniqueResult();
        transaction.commit();
        return user;
    }

    private static Session getSession() {
        Session currentSession = HibernateUtil.getSessionFactory().getCurrentSession();
        if (!currentSession.isOpen())
            currentSession = HibernateUtil.getSessionFactory().openSession();
        return currentSession;
    }

    public static List<FeedItem> getFeedItems(int feedId) {
        Session session = getSession();

        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM FeedItem WHERE feed = :feed");
        query.setParameter("feed", feedId);

        List<FeedItem> allFeedItems = query.list();
        transaction.commit();
        return allFeedItems;
    }
}
