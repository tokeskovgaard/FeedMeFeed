package dk.tokebroedsted.hibernate;

import dk.tokebroedsted.user.client.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.rmi.runtime.Log;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    private static Logger logger = LoggerFactory.getLogger(HibernateUtil.class);

    static {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            sessionFactory = configuration.configure().buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void saveSomething(Object object) {
        logger.info("Save called for: " + object.toString());
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.save(object);
        transaction.commit();
    }

    public static Object getSomething(Class clazz, Serializable id) {
        logger.info("Get called for: " + id);
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Object load = session.load(clazz, id);
        if(load != null)
        {
            logger.info("Something was found for id " + id + ". It was " + load.toString());
        }
        transaction.commit();
        return load;
    }

    public static Object getUsers() {
        logger.info("Initiating getUsers()");
        Configuration config = new Configuration();
        config.addAnnotatedClass(dk.tokebroedsted.hibernate.tables.User.class);
        ArrayList<User> users = new ArrayList<User>();

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query queryResult = session.createQuery("from User");
        java.util.List allUsers;
        allUsers = queryResult.list();
        for (int i = 0; i < allUsers.size(); i++) {
            dk.tokebroedsted.hibernate.tables.User user = (dk.tokebroedsted.hibernate.tables.User) allUsers.get(i);
            logger.info("We found " + user.getUsername() + " users with our query.");
            User mUser = new User(user.getId(), user.getLoginname(), user.getUsername(), user.getPassword(), user.getEmail());
            users.add(mUser);
        }
        session.getTransaction().commit();
        return users;

    }

    public static void deleteUser(int id) {

        logger.info("deleteUser called with: " + id);
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        dk.tokebroedsted.hibernate.tables.User user = new dk.tokebroedsted.hibernate.tables.User();
        user.setId(id);
        session.delete(user);
        session.getTransaction().commit();

    }



    public static void createUser(User user) {
        logger.info("createUser called for: " + user.getLoginname());
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        String password = "";
        try { password = hashPassword(user.getPassword());} catch (Exception e ) { throw new RuntimeException(e); }
        dk.tokebroedsted.hibernate.tables.User tUser = new dk.tokebroedsted.hibernate.tables.User(user.getId(), user.getLoginname(), user.getUsername(), password, user.getEmail());
        session.save(tUser);
        transaction.commit();
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