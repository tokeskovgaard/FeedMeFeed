package dk.tokebroedsted.hibernate;

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
        logger.debug("Save called for: "+object.toString());
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.save(object);
        transaction.commit();
    }

    public static Object getSomething(Class clazz, Serializable id) {
        logger.debug("Get called for: "+id);
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Object load = session.load(clazz, id);
        transaction.commit();
        return load;
    }
}