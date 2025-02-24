package com.test.resources.database;

import com.test.annotations.Component;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.test.annotations.Bean;

@Component
public class HibernateUtil {
    private static final Logger logger = LogManager.getLogger(HibernateUtil.class);

    @Bean
    public static SessionFactory sessionFactory() {
        try {
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            logger.info("SessionFactory created successfully.");
            return sessionFactory;
        } catch (Exception e) {
            logger.error("Failed to create SessionFactory: " + e.getMessage(), e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static void shutdown(SessionFactory sessionFactory) {
        if (sessionFactory != null) {
            sessionFactory.close();
            logger.info("SessionFactory closed.");
        }
    }
}