package com.test.resources.database;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HibernateUtil {
    private static final Logger logger = LogManager.getLogger(HibernateUtil.class);
    private static SessionFactory sessionFactory;

    static {
        try {
            // Создаём SessionFactory из hibernate.cfg.xml
            sessionFactory = new Configuration().configure().buildSessionFactory();
            logger.info("SessionFactory created successfully.");
        } catch (Exception e) {
            logger.error("Failed to create SessionFactory: " + e.getMessage(), e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
            logger.info("SessionFactory closed.");
        }
    }
}