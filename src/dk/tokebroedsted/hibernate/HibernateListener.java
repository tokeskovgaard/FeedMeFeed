package dk.tokebroedsted.hibernate;


import org.hibernate.SessionFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class HibernateListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent event) {
        HibernateUtil.getSessionFactory();// Just call the static initializer of that class
    }

    public void contextDestroyed(ServletContextEvent event) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        sessionFactory.close(); // Free all resources
    }
}