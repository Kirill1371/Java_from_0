package ru.senla.javacourse.tarasov.hotel.impl.repository.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.senla.javacourse.tarasov.hotel.db.entity.Guest;
import ru.senla.javacourse.tarasov.hotel.db.entity.Room;
import ru.senla.javacourse.tarasov.hotel.db.entity.Service;
import ru.senla.javacourse.tarasov.hotel.db.entity.Stay;
import ru.senla.javacourse.tarasov.hotel.ioc.annotations.Component;
import ru.senla.javacourse.tarasov.hotel.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.senla.javacourse.tarasov.hotel.db.entity.Guest;
import ru.senla.javacourse.tarasov.hotel.impl.repository.GuestRepository;

@Component
public class GuestRepositoryImpl implements GuestRepository {


    private static final Logger logger = LogManager.getLogger(GuestRepositoryImpl.class);
    @Inject
    private SessionFactory sessionFactory;

    @Override
    public Guest getGuest(String name) {
        try (Session session = sessionFactory.openSession()) {
            Query<Guest> query = session.createQuery("FROM Guest g WHERE g.name = :name", Guest.class);
            query.setParameter("name", name);
            Guest guest = query.uniqueResult();

            if (guest != null) {
                // Инициализируем ленивую коллекцию услуг
                guest.getServices().size(); // Это вызовет загрузку коллекции
            }

            return guest;
        } catch (Exception e) {
            logger.error("Error while fetching guest: " + e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<Guest> getAllGuests() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Guest", Guest.class).list();
        } catch (Exception e) {
            logger.error("Error while fetching guests: " + e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public void addGuest(Guest guest) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(guest);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            logger.error("Error while adding guest: " + e.getMessage(), e);
        }
    }

    public Guest getGuestByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Guest g WHERE g.name = :name", Guest.class)
                    .setParameter("name", name)
                    .uniqueResult();
        } catch (Exception e) {
            logger.error("Error while fetching guest by name: " + e.getMessage(), e);
            return null;
        }
    }
}
