package ru.senla.javacourse.tarasov.hotel.impl.repository.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.senla.javacourse.tarasov.hotel.db.entity.Guest;
import ru.senla.javacourse.tarasov.hotel.db.entity.Service;
import ru.senla.javacourse.tarasov.hotel.impl.repository.ServiceRepository;
import ru.senla.javacourse.tarasov.hotel.ioc.annotations.Component;
import ru.senla.javacourse.tarasov.hotel.ioc.annotations.Inject;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ServiceRepositoryImpl implements ServiceRepository {

    private static final Logger logger = LogManager.getLogger(ServiceRepositoryImpl.class);
    @Inject
    private SessionFactory sessionFactory;

    @Override
    public void addService(String guestName, Service service) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            Guest guest = session.createQuery("FROM Guest WHERE name = :guestName", Guest.class)
                    .setParameter("guestName", guestName)
                    .uniqueResult();

            if (guest != null) {
                service.setGuest(guest);
                session.persist(service);
                transaction.commit();
                logger.info("Service added for guest: " + guestName);
            } else {
                if (transaction != null) {
                    transaction.rollback();
                }
                logger.warn("Guest not found: " + guestName);
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error while adding service for guest: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Service> getServicesForGuest(String guestName) {
        try (Session session = sessionFactory.openSession()) {
            Guest guest = session.createQuery("FROM Guest WHERE name = :guestName", Guest.class)
                    .setParameter("guestName", guestName)
                    .uniqueResult();

            if (guest != null) {
                return guest.getServices();
            } else {
                logger.warn("Guest not found: " + guestName);
                return new ArrayList<>();
            }
        } catch (Exception e) {
            logger.error("Error while fetching services for guest: " + e.getMessage(), e);
            return new ArrayList<>();
        }
    }
}
