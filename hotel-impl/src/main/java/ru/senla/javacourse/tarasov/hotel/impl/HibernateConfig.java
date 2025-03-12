package ru.senla.javacourse.tarasov.hotel.impl;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration.*;

@org.springframework.context.annotation.Configuration
@ComponentScan(basePackages = "ru.senla.javacourse.tarasov.hotel")
public class HibernateConfig {

    @Bean
    public SessionFactory sessionFactory() {
        return new org.hibernate.cfg.Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
    }
}

















