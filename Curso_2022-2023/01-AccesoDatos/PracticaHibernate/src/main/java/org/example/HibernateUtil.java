package org.example;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

        private static final SessionFactory sessionFactory;

        static {
            try {
                // Create the SessionFactory from hibernate.cfg.xml
                sessionFactory = new Configuration().configure().buildSessionFactory();
            } catch (Throwable ex) {
                // Make sure you log the exception, as it might be swallowed
                System.err.println("Error al crear SessionFactory :" + ex);
                throw new ExceptionInInitializerError(ex);
            }
        }

        public static SessionFactory getSessionFactory() {
            return sessionFactory;
        }
}
