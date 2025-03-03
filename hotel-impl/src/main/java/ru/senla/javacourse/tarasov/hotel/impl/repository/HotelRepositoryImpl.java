package ru.senla.javacourse.tarasov.hotel.impl.repository;








import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import javax.management.Query;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.senla.javacourse.tarasov.hotel.db.entity.Guest;
import ru.senla.javacourse.tarasov.hotel.db.entity.Room;
import ru.senla.javacourse.tarasov.hotel.db.entity.Service;
import ru.senla.javacourse.tarasov.hotel.db.entity.Stay;
import ru.senla.javacourse.tarasov.hotel.db.entity.database.DatabaseConnection;
import ru.senla.javacourse.tarasov.hotel.ioc.annotations.Component;
import ru.senla.javacourse.tarasov.hotel.ioc.annotations.Inject;


@Component
public class HotelRepositoryImpl implements HotelRepository {

    private static final Logger logger = LogManager.getLogger(HotelRepositoryImpl.class);
    private final DatabaseConnection databaseConnection;

    @Inject
    public HotelRepositoryImpl(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }


    @Inject
    private SessionFactory sessionFactory;

    public Room getRoomFromDatabase(int roomNumber) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Room WHERE number = :roomNumber", Room.class)
                    .setParameter("roomNumber", roomNumber)
                    .uniqueResult();
        } catch (Exception e) {
            logger.error("Error retrieving room details: " + e.getMessage(), e);
            return null;
        }
    }

//    public void addRoomToDatabase(int roomNumber, double price, int capacity, int stars) {
//        Transaction transaction = null;
//        try (Session session = sessionFactory.openSession()) {
//            transaction = session.beginTransaction();
//
//            Room room = new Room(roomNumber, "Available", price, capacity, stars); // Статус по умолчанию "Available"
//            session.persist(room);
//            transaction.commit();
//            logger.info("Room added to database: " + room.getNumber());
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            logger.error("Error adding room to database: " + e.getMessage(), e);
//            throw e;
//        }
//    }

    public void addRoomToDatabase(Room room) {
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

    public void removeRoomFromDatabase(int roomNumber) {
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

    public List<Room> getAllRooms() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Room", Room.class).list();
        } catch (Exception e) {
            logger.error("Error fetching all rooms: " + e.getMessage(), e);
            throw e;
        }
    }

    public List<Room> getAvailableRooms() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Room WHERE status = 'Available'", Room.class).list();
        } catch (Exception e) {
            logger.error("Error fetching available rooms: " + e.getMessage(), e);
            throw e;
        }
    }

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
    public Room getRoomById(UUID id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Room.class, id);
        } catch (Exception e) {
            logger.error("Error while fetching room by ID: " + e.getMessage(), e);
            return null;
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

    @Override
    public List<Guest> getAllGuests() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Guest", Guest.class).list();
        } catch (Exception e) {
            logger.error("Error while fetching guests: " + e.getMessage(), e);
            return Collections.emptyList();
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
}


