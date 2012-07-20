package dk.tokebroedsted.hibernate;

import dk.tokebroedsted.commons.client.models.FeedGWT;
import dk.tokebroedsted.commons.client.models.UserGWT;
import dk.tokebroedsted.commons.server.converters.FeedConverter;
import dk.tokebroedsted.commons.server.converters.UserConverter;
import dk.tokebroedsted.hibernate.tables.Feed;
import dk.tokebroedsted.user.client.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Set;

public class HibernateUtil {

    private static Logger logger = LoggerFactory.getLogger(HibernateUtil.class);


    public static Object getSomething(Class clazz, Serializable id) {
        logger.info("Get called for: " + id);
        Session session = HibernateHelper.getCurrentSession();


        Object load;
        try {
            HibernateHelper.beginTransaction();
            load = session.load(clazz, id);
            HibernateHelper.commitTransaction();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }

        return load;
    }

    public static ArrayList<UserGWT> getUsers() {
        logger.info("Initiating getUsers()");
        Configuration config = new Configuration();
        config.addAnnotatedClass(dk.tokebroedsted.hibernate.tables.User.class);
        ArrayList<UserGWT> users = new ArrayList<UserGWT>();

        Session session = HibernateHelper.getCurrentSession();
        try {

            HibernateHelper.beginTransaction();
            Query queryResult = session.createQuery("from User");
            java.util.List allUsers;
            allUsers = queryResult.list();
            for (int i = 0; i < allUsers.size(); i++) {
                dk.tokebroedsted.hibernate.tables.User user = (dk.tokebroedsted.hibernate.tables.User) allUsers.get(i);
                logger.info("We found " + user.getUsername() + " users with our query.");
                UserGWT mUser = new UserGWT(user.getId(), user.getLoginname(), user.getUsername(), user.getPassword(), user.getEmail());
                users.add(mUser);
            }
            HibernateHelper.commitTransaction();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
        return users;

    }

    public static void deleteUser(int id) {

        logger.info("deleteUser called with: " + id);
        Session session = HibernateHelper.getCurrentSession();

        try {
            session.beginTransaction();
            dk.tokebroedsted.hibernate.tables.User user = new dk.tokebroedsted.hibernate.tables.User();
            user.setId(id);
            session.delete(user);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
    }


    public static void createUser(UserGWT user) {
        logger.info("createUser called for: " + user.getLoginname());
        Session session = HibernateHelper.getCurrentSession();
        HibernateHelper.beginTransaction();
        String password = "";
        try {
            password = hashPassword(user.getPassword());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        dk.tokebroedsted.hibernate.tables.User tUser = new dk.tokebroedsted.hibernate.tables.User(0, user.getLoginname(), user.getUsername(), password, user.getEmail());
        session.save(tUser);
        HibernateHelper.commitTransaction();
    }

    public static User getUser(String loginname) {
        logger.info("Initiating getUser(" + loginname + ")");
        Configuration config = new Configuration();
        config.addAnnotatedClass(dk.tokebroedsted.hibernate.tables.User.class);
        ArrayList<User> users = new ArrayList<User>();

        Session session = HibernateHelper.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from User where loginname = :loginname");

        query.setParameter("loginname", loginname);
        java.util.List allUsers;
        allUsers = query.list();
        for (int i = 0; i < allUsers.size(); i++) {
            dk.tokebroedsted.hibernate.tables.User user = (dk.tokebroedsted.hibernate.tables.User) allUsers.get(i);
            logger.info("We found " + user.getUsername() + " users with our query.");
            User mUser = new User(user.getId(), user.getLoginname(), user.getUsername(), user.getPassword(), user.getEmail());
            users.add(mUser);
        }
        session.getTransaction().commit();
        if (users.size() > 0) {
            return users.get(0);
        }
        return null;
    }

    public static UserGWT getUser(String loginname, String password) {
        logger.info("Initiating getUser with " + loginname + " and " + password);
        Configuration config = new Configuration();
        config.addAnnotatedClass(dk.tokebroedsted.hibernate.tables.User.class);
        ArrayList<UserGWT> users = new ArrayList<UserGWT>();

        Session session = HibernateHelper.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from User where loginname = :loginname and password = :password");

        query.setParameter("loginname", loginname);
        query.setParameter("password", password);

        java.util.List allUsers;
        allUsers = query.list();
        for (int i = 0; i < allUsers.size(); i++) {
            dk.tokebroedsted.hibernate.tables.User user = (dk.tokebroedsted.hibernate.tables.User) allUsers.get(i);
            logger.info("We found " + user.getUsername() + " users with our query.");
            UserConverter userConverter = new UserConverter();
            UserGWT mUser = userConverter.toGwtObject(user);
            //User mUser = new User(user.getId(), user.getLoginname(), user.getUsername(), user.getPassword(), user.getEmail());
            users.add(mUser);
        }
        session.getTransaction().commit();
        if (users.size() > 0) {
            return users.get(0);
        }
        return null;
    }

    public static ArrayList<FeedGWT> getSubscribtions(UserGWT userGWT) {
        logger.info("Initiating getSubscribtions(" + userGWT.getLoginname() + ")");
        ArrayList<FeedGWT> feeds = new ArrayList<FeedGWT>();

        Configuration config = new Configuration();
        config.addAnnotatedClass(dk.tokebroedsted.hibernate.tables.Feed.class);

        Session session = HibernateHelper.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from User where id = :user_id");

        query.setParameter("user_id", userGWT.getId());


        // Vi finder en
        dk.tokebroedsted.hibernate.tables.User user = (dk.tokebroedsted.hibernate.tables.User) query.list().get(0);
        Set<Feed> allFeeds = user.getFeedSubscriptions();
        session.getTransaction().commit();
        FeedConverter feedConverter = new FeedConverter();
        for (Feed feed : allFeeds) {
            feeds.add(feedConverter.toGwtObject(feed));
        }

        return feeds;
    }


    private static String hashPassword(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes("UTF-8"));

        byte byteData[] = md.digest();

        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();

    }


}