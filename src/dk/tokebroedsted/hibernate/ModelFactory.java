package dk.tokebroedsted.hibernate;

import dk.tokebroedsted.hibernate.tables.*;
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

    public static List<Feed> getAllFeeds() {
        Session session = getSession();

        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM Feed");

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
        Query query = session.createQuery("FROM FeedItem WHERE feed_id = :feed");
        query.setParameter("feed", feedId);

        List<FeedItem> allFeedItems = query.list();
        transaction.commit();
        return allFeedItems;
    }

    public static void save(Object objectToSave) {
        Session session = getSession();

        try {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(objectToSave);
            transaction.commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    public static List<FeedItem> getAllFeedItems() {
        Session session = getSession();
        try {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("FROM FeedItem");

            List<FeedItem> allFeedItems = query.list();
            transaction.commit();
            return allFeedItems;
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    public static List<User> getAllUsers() {
        Session session = getSession();
        try {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("FROM User");

            List<User> allFeedItems = query.list();
            transaction.commit();
            return allFeedItems;
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    public static Feed getFeed(int feedId) {
        Session session = getSession();
        try {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Feed WHERE id = :feedId");
            query.setParameter("feedId", feedId);
            Feed feed = (Feed) query.uniqueResult();
            transaction.commit();
            return feed;
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    public static FeedInput getInput(Integer inputId) {
        Session session = getSession();
        try {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("FROM FeedInput WHERE id = :inputId");
            query.setParameter("inputId", inputId);
            FeedInput feedInput = (FeedInput) query.uniqueResult();
            transaction.commit();
            return feedInput;
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    public static List<FeedInput> getAllFeedInputs() {
        Session session = getSession();
        try {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("FROM FeedInput");

            List<FeedInput> allFeedInputs = query.list();
            transaction.commit();
            return allFeedInputs;
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    public static List<FeedItemInput> getAllFeedInputItems() {
        Session session = getSession();
        try {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("FROM FeedItemInput");

            List<FeedItemInput> allFeedItemInputs = query.list();
            transaction.commit();
            return allFeedItemInputs;
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}
