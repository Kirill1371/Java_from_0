package com.test.repository;

import com.test.model.Room;
import com.test.model.Service;
import com.test.model.Stay;
import com.test.resources.database.DatabaseConnection;
import com.test.model.Guest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.test.annotations.Inject;
import com.test.annotations.Component;
import com.test.resources.database.HibernateUtil;
import org.hibernate.query.Query;


@Component
public class HotelRepository implements IHotelRepository {

    private static final Logger logger = LogManager.getLogger(RoomRepository.class);
    private final DatabaseConnection databaseConnection;

    @Inject
    public HotelRepository(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public Room getRoomFromDatabase(int roomNumber) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Используем HQL (Hibernate Query Language) для поиска комнаты по номеру
            return session.createQuery("FROM Room WHERE number = :roomNumber", Room.class)
                    .setParameter("roomNumber", roomNumber)
                    .uniqueResult();
        } catch (Exception e) {
            System.out.println("Error retrieving room details: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }


    public void addRoomToDatabase(int roomNumber, double price, int capacity, int stars) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Room room = new Room(roomNumber, "Available", price, capacity, stars); // Статус по умолчанию "Available"

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

    public void removeRoomFromDatabase(int roomNumber) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
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

    public Room getRoom(int roomNumber) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Room WHERE number = :number", Room.class)
                    .setParameter("number", roomNumber)
                    .uniqueResult();
        } catch (Exception e) {
            logger.error("Error fetching room: " + e.getMessage(), e);
            throw e;
        }
    }

    public List<Room> getAllRooms() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Room", Room.class).list();
        } catch (Exception e) {
            logger.error("Error fetching all rooms: " + e.getMessage(), e);
            throw e;
        }
    }

    public List<Room> getAvailableRooms() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Room WHERE status = 'Available'", Room.class).list();
        } catch (Exception e) {
            logger.error("Error fetching available rooms: " + e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void addService(String guestName, Service service) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Guest guest = session.createQuery("FROM Guest WHERE name = :guestName", Guest.class)
                    .setParameter("guestName", guestName)
                    .uniqueResult();

            if (guest != null) {
                service.setGuest(guest);
                session.persist(service);
                transaction.commit();
                System.out.println("Service added for guest: " + guestName);
            } else {
                if (transaction != null) {
                    transaction.rollback();
                }
                System.out.println("Guest not found: " + guestName);
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error while adding service for guest: " + e.getMessage());
        }
    }

    @Override
    public List<Service> getServicesForGuest(String guestName) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Guest guest = session.createQuery("FROM Guest WHERE name = :guestName", Guest.class)
                    .setParameter("guestName", guestName)
                    .uniqueResult();

            if (guest != null) {
                return guest.getServices();
            } else {
                System.out.println("Guest not found: " + guestName);
                return new ArrayList<>();
            }
        } catch (Exception e) {
            System.out.println("Error while fetching services for guest: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void addGuest(Guest guest) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(guest);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.out.println("Error while adding guest: " + e.getMessage());
        }
    }

    public Guest getGuestByName(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Guest g WHERE g.name = :name", Guest.class)
                    .setParameter("name", name)
                    .uniqueResult();
        }
    }


    @Override
    public Guest getGuest(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Guest> query = session.createQuery("FROM Guest g WHERE g.name = :name", Guest.class);
            query.setParameter("name", name);
            Guest guest = query.uniqueResult();

            if (guest != null) {
                Hibernate.initialize(guest.getServices());
            }

            return guest;
        } catch (Exception e) {
            System.out.println("Error while fetching guest: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void addStay(Stay stay) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.persist(stay);

            transaction.commit();
            System.out.println("Stay added successfully!");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error while adding stay: " + e.getMessage());
        }
    }


    @Override
    public Room getRoomById(UUID id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Room.class, id);
        } catch (Exception e) {
            System.out.println("Error while fetching room: " + e.getMessage());
            return null;
        }
    }


    @Override
    public List<Stay> getAllStays() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Stay", Stay.class).list();
        } catch (Exception e) {
            System.out.println("Error while fetching stays: " + e.getMessage());
            return Collections.emptyList();
        }
    }


    @Override
    public List<Guest> getAllGuests() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Guest", Guest.class).list();
        } catch (Exception e) {
            System.out.println("Error while fetching guests: " + e.getMessage());
            return Collections.emptyList();
        }
    }



    @Override
    public void updateRoomStatus(int roomNumber, String newStatus) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Query query = session.createQuery("UPDATE Room r SET r.status = :status WHERE r.number = :number");
            query.setParameter("status", newStatus);
            query.setParameter("number", roomNumber);

            int rowsAffected = query.executeUpdate();
            transaction.commit();

            if (rowsAffected > 0) {
                System.out.println("Room status updated successfully!");
            } else {
                System.out.println("No room found with number: " + roomNumber);
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error while updating room status: " + e.getMessage());
        }
    }

    @Override
    public void setStatusAv(int roomNumber) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Query query = session.createQuery("UPDATE Room r SET r.status = 'Available' WHERE r.number = :number");
            query.setParameter("number", roomNumber);

            int rowsAffected = query.executeUpdate();
            transaction.commit();

            if (rowsAffected > 0) {
                System.out.println("Room " + roomNumber + " status updated to Available.");
            } else {
                System.out.println("Room not found or already checked out.");
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error while updating room status: " + e.getMessage());
        }
    }

}
