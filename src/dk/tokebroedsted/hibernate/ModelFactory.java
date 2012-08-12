package dk.tokebroedsted.hibernate;

import dk.tokebroedsted.hibernate.tables.*;
import org.hibernate.*;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.engine.spi.TypedValue;

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
        Session session = HibernateHelper.getCurrentSession();

        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM Feed WHERE owner = :owner");
        query.setParameter("owner", user);

        List<Feed> allFeeds = query.list();
        transaction.commit();
        return allFeeds;
    }

    public static List<Feed> getAllFeeds() {
        Session session = HibernateHelper.getCurrentSession();

        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM Feed");

        List<Feed> allFeeds = query.list();
        transaction.commit();
        return allFeeds;
    }

    public static User getUser(String username) {
        Session session = HibernateHelper.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM User WHERE username = :username");
        query.setParameter("username", username);
        User user = (User) query.uniqueResult();
        transaction.commit();
        return user;
    }


    public static List<FeedItem> getFeedItems(int feedId) {
        Session session = HibernateHelper.getCurrentSession();

        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM FeedItem WHERE feed_id = :feed");
        query.setParameter("feed", feedId);

        List<FeedItem> allFeedItems = query.list();
        transaction.commit();
        return allFeedItems;
    }

    public static void save(Object objectToSave) {
        Session session = HibernateHelper.getCurrentSession();

        try {
            Transaction transaction = session.beginTransaction();
            session.save(objectToSave);
            transaction.commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    public static List<FeedItem> getAllFeedItems() {
        Session session = HibernateHelper.getCurrentSession();
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
        Session session = HibernateHelper.getCurrentSession();
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
        Session session = HibernateHelper.getCurrentSession();
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
        Session session = HibernateHelper.getCurrentSession();
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
        Session session = HibernateHelper.getCurrentSession();
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
        Session session = HibernateHelper.getCurrentSession();
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

    public static Question getQuestion(int questionId) {
        Session session = HibernateHelper.getCurrentSession();
        try {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Question WHERE id = :questionId");
            query.setParameter("questionId", questionId);
            Question question = (Question) query.uniqueResult();
            transaction.commit();
            return question;
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    public static QuestionItem getQuestionItem(Integer questionItemId) {
        Session session = HibernateHelper.getCurrentSession();
        try {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("FROM QuestionItem WHERE id = :questionItemId");
            query.setParameter("questionItemId", questionItemId);
            QuestionItem questionItem = (QuestionItem) query.uniqueResult();
            transaction.commit();
            return questionItem;
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }

    }

    public static FeedItem getFeedItem(Integer feedItemId) {
        Session session = HibernateHelper.getCurrentSession();
        try {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("FROM FeedItem WHERE id = :feedItemId");
            query.setParameter("feedItemId", feedItemId);
            FeedItem feedItem = (FeedItem) query.uniqueResult();
            transaction.commit();
            return feedItem;
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    public static List<Question> getAllQuestions() {
        Session session = HibernateHelper.getCurrentSession();
        try {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Question");
            List<Question> questions = query.list();
            transaction.commit();
            return questions;
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }

    }

    public static List<QuestionItem> getAllFeedQuestionItems() {
        Session session = HibernateHelper.getCurrentSession();
        try {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("FROM QuestionItem");
            List<QuestionItem> questionItems = query.list();
            transaction.commit();
            return questionItems;
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
    }


    public static <T> T getModelObject(Class<T> clazz, Integer id) {
        Session session = HibernateHelper.getCurrentSession();
        try {
            Transaction transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(clazz);
            criteria.add(Restrictions.eq("id", id));
            T result = (T) criteria.uniqueResult();
            transaction.commit();
            return result;
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }

    }

    public static List<Calculation> getAllCalculations() {
        Session session = HibernateHelper.getCurrentSession();
        try {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Calculation ");
            List<Calculation> calculations = query.list();
            transaction.commit();
            return calculations;
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    public static void delete(Class clazz, int id) {
        Session session = HibernateHelper.getCurrentSession();
        try {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM " + clazz.getSimpleName() + " WHERE id = :id ");
            query.setParameter("id", id);
            int row = query.executeUpdate();
            transaction.commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    public static User getUser(String username, String password) {
        Session session = HibernateHelper.getCurrentSession();
        try {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("FROM User WHERE username = :username AND password = :password");
            query.setParameter("username", username);
            query.setParameter("password", password);
            User user = (User) query.uniqueResult();
            transaction.commit();
            return user;
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    public static void deleteUser(int id) {
        Session session = HibernateHelper.getCurrentSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            SQLQuery query = session.createSQLQuery("DELETE FROM User WHERE Id = :Id");
            query.setParameter("Id", id);
            int result = query.executeUpdate();
            if (result == 0)

                transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            throw e;
        }
    }


}
