package dk.tokebroedsted.hibernate;


import org.hibernate.SessionFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class HibernateListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent event) {
        HibernateHelper.getCurrentSession(); // Just call the static initializer of that class
    }

    public void contextDestroyed(ServletContextEvent event) {
        HibernateHelper.closeSession();
    }
}