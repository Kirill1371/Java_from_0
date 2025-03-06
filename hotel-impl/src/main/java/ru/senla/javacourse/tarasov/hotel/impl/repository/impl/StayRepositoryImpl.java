package ru.senla.javacourse.tarasov.hotel.impl.repository.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.senla.javacourse.tarasov.hotel.db.entity.Stay;
//import ru.senla.javacourse.tarasov.hotel.impl.repository.HotelRepositoryImpl;
import ru.senla.javacourse.tarasov.hotel.impl.repository.StayRepository;
import ru.senla.javacourse.tarasov.hotel.ioc.annotations.Component;
import ru.senla.javacourse.tarasov.hotel.ioc.annotations.Inject;

import java.util.Collections;
import java.util.List;

@Component
public class StayRepositoryImpl implements StayRepository {

    private static final Logger logger = LogManager.getLogger(StayRepositoryImpl.class);
    @Inject
    private SessionFactory sessionFactory;

    @Override
    public void addStay(Stay stay) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(stay);
            transaction.commit();
            logger.info("Stay added successfully!");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error while adding stay: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Stay> getAllStays() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Stay", Stay.class).list();
        } catch (Exception e) {
            logger.error("Error while fetching stays: " + e.getMessage(), e);
            return Collections.emptyList();
        }
    }
}
