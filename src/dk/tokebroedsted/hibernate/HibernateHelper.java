package dk.tokebroedsted.hibernate;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public final class HibernateHelper {
    static Logger mLogger = Logger.getLogger(HibernateHelper.class);

    private static final ThreadLocal<Session> threadSession = new ThreadLocal();
    private static final ThreadLocal<Transaction> threadTransaction = new ThreadLocal();
    private static SessionFactory mSessionFactory;

    static {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            mSessionFactory = configuration.configure().buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            mLogger.error("Initial SessionFactory creation failed." + ex);
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getCurrentSession() {
        Session s = threadSession.get();
        try {
            if (s == null || !s.isOpen()) {
                if (mLogger.isInfoEnabled()) {
                    mLogger.info("Opening new Session for this thread.");
                }
                s = mSessionFactory.openSession();
                threadSession.set(s);
            } else {
                if (mLogger.isInfoEnabled()) {
                    mLogger.info("Using current session in this thread.");
                }
            }
        } catch (HibernateException ex) {
            mLogger.error("unable to open hibernate session", ex);
            throw new RuntimeException("unable to open hibernate session", ex);
        }
        return s;
    }

    public static void closeSession() {
        try {
            final Session s = threadSession.get();
            if (s != null && s.isOpen()) {
                mLogger.info("Closing Session of this thread.");
                s.close();
            }
        } catch (HibernateException ex) {
            mLogger.error("unable to close hibernate session", ex);
            throw new RuntimeException("unable to close hibernate session", ex);
        } finally {
            threadSession.set(null);
        }
    }

    public static void beginTransaction() {
        Transaction tx = threadTransaction.get();
        try {
            if (tx != null && !tx.isActive()) {
                tx = null;
                threadTransaction.set(null);
            }
            if (tx == null) {
                if (mLogger.isInfoEnabled()) {
                    mLogger.info("Starting new database transaction in this thread.");
                }
                if (threadSession.get() != null && threadSession.get().isOpen()) {
                    threadSession.get().close();
                    threadSession.set(null);
                }
                tx = getCurrentSession().beginTransaction();
                threadTransaction.set(tx);
            } else {
                if (mLogger.isInfoEnabled()) {
                    mLogger.info("Using current database transaction in this thread.");
                    mLogger.info("Opening new Session for this thread.");
                }
            }
        } catch (HibernateException ex) {
            mLogger.error("unable to begin hibernate transaction", ex);
            throw new RuntimeException("unable to begin hibernate transaction", ex);
        } finally {
            if (threadSession.get() == null || !threadSession.get().isOpen()) {
                getCurrentSession();
            } else {
                threadSession.get().clear();
            }
        }
    }

    public static void commitTransaction() {
        final Transaction tx = threadTransaction.get();
        try {
            if (tx != null && !tx.wasCommitted() && !tx.wasRolledBack()) {
                Session s = getCurrentSession();
                s.flush();
                if (mLogger.isInfoEnabled()) {
                    mLogger.info("Flushing session and committing transaction of this thread.");
                }
                tx.commit();
            }
        } catch (HibernateException ex) {
            rollbackTransaction();
            mLogger.error("unable to commit hibernate transaction", ex);
            throw new RuntimeException("unable to commit hibernate transaction", ex);
        } finally {
            threadTransaction.set(null);
//            closeSession();
        }
    }

    public static void rollbackTransaction() {
        final Transaction tx = threadTransaction.get();
        try {
            if (tx != null && !tx.wasCommitted() && !tx.wasRolledBack()) {
                mLogger.info("Trying to rollback database transaction of this thread.");
                tx.rollback();
            }
        } catch (HibernateException ex) {
            mLogger.error("unable to rollback hibernate transaction", ex);
            throw new RuntimeException("unable to rollback hibernate transaction", ex);
        } finally {
            threadTransaction.set(null);
//            closeSession();
        }
    }
}