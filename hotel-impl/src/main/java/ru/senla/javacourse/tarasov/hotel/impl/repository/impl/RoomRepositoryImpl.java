package ru.senla.javacourse.tarasov.hotel.impl.repository.impl;

import java.util.List;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.senla.javacourse.tarasov.hotel.db.entity.Room;
//import ru.senla.javacourse.tarasov.hotel.impl.repository.HotelRepository;
import ru.senla.javacourse.tarasov.hotel.impl.repository.RoomRepository;
import ru.senla.javacourse.tarasov.hotel.ioc.annotations.Component;
import ru.senla.javacourse.tarasov.hotel.ioc.annotations.Inject;

@Repository
public class RoomRepositoryImpl implements RoomRepository {

    private static final Logger logger = LogManager.getLogger(RoomRepositoryImpl.class);

    private SessionFactory sessionFactory;

    @Autowired // Внедрение зависимости через конструктор
    public RoomRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addRoom(Room room) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(room);
            transaction.commit();
            logger.info("Room added to database: " + room.getNumber());
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error adding room to database: " + e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public Room getRoom(int roomNumber) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Room WHERE number = :number", Room.class)
                    .setParameter("number", roomNumber)
                    .uniqueResult();
        } catch (Exception e) {
            logger.error("Error fetching room: " + e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public List<Room> getAllRooms() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Room", Room.class).list();
        } catch (Exception e) {
            logger.error("Error fetching all rooms: " + e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public List<Room> getAvailableRooms() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Room WHERE status = 'Available'", Room.class).list();
        } catch (Exception e) {
            logger.error("Error fetching available rooms: " + e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void setStatusAv(int roomNumber) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            Query query = session.createQuery("UPDATE Room r SET r.status = 'Available' WHERE r.number = :number");
            query.setParameter("number", roomNumber);

            int rowsAffected = query.executeUpdate();
            transaction.commit();

            if (rowsAffected > 0) {
                logger.info("Room " + roomNumber + " status updated to Available.");
            } else {
                logger.warn("Room not found or already checked out.");
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error while updating room status: " + e.getMessage(), e);
        }
    }

    @Override
    public Room getRoomById(UUID id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Room.class, id);
        } catch (Exception e) {
            logger.error("Error while fetching room by ID: " + e.getMessage(), e);
            return null;
        }
    }

    @Override
    public void updateRoomStatus(int roomNumber, String newStatus) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            Query
                    query = session.createQuery("UPDATE Room r SET r.status = :status WHERE r.number = :number");
            query.setParameter("status", newStatus);
            query.setParameter("number", roomNumber);

            int rowsAffected = query.executeUpdate();
            transaction.commit();

            if (rowsAffected > 0) {
                logger.info("Room status updated successfully!");
            } else {
                logger.warn("No room found with number: " + roomNumber);
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error while updating room status: " + e.getMessage(), e);
        }
    }

    @Override
    public void removeRoom(int roomNumber) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Room room = session.createQuery("FROM Room WHERE number = :number", Room.class)
                    .setParameter("number", roomNumber)
                    .uniqueResult();
            if (room != null) {
                session.remove(room);
                logger.info("Room removed from database: " + roomNumber);
            } else {
                logger.warn("Room not found: " + roomNumber);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error removing room from database: " + e.getMessage(), e);
            throw e;
        }
    }


}



