//package com.test.repository;
//
//import com.test.annotations.Component;
//import com.test.model.Room;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import com.test.resources.database.HibernateUtil;
//
//import java.util.List;
//
//@Component
//public class RoomRepository {
//
//    private static final Logger logger = LogManager.getLogger(RoomRepository.class);
//
//    public void addRoomToDatabase(Room room) {
//        Transaction transaction = null;
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
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
//
//    public void removeRoomFromDatabase(int roomNumber) {
//        Transaction transaction = null;
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            Room room = session.createQuery("FROM Room WHERE number = :number", Room.class)
//                    .setParameter("number", roomNumber)
//                    .uniqueResult();
//            if (room != null) {
//                session.remove(room);
//                logger.info("Room removed from database: " + roomNumber);
//            } else {
//                logger.warn("Room not found: " + roomNumber);
//            }
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            logger.error("Error removing room from database: " + e.getMessage(), e);
//            throw e;
//        }
//    }
//
//    public Room getRoom(int roomNumber) {
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            return session.createQuery("FROM Room WHERE number = :number", Room.class)
//                    .setParameter("number", roomNumber)
//                    .uniqueResult();
//        } catch (Exception e) {
//            logger.error("Error fetching room: " + e.getMessage(), e);
//            throw e;
//        }
//    }
//
//    public List<Room> getAllRooms() {
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            return session.createQuery("FROM Room", Room.class).list();
//        } catch (Exception e) {
//            logger.error("Error fetching all rooms: " + e.getMessage(), e);
//            throw e;
//        }
//    }
//
//    public List<Room> getAvailableRooms() {
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            return session.createQuery("FROM Room WHERE status = 'Available'", Room.class).list();
//        } catch (Exception e) {
//            logger.error("Error fetching available rooms: " + e.getMessage(), e);
//            throw e;
//        }
//    }
//
//    public void updateRoomStatus(int roomNumber, String status) {
//        Transaction transaction = null;
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            Room room = session.createQuery("FROM Room WHERE number = :number", Room.class)
//                    .setParameter("number", roomNumber)
//                    .uniqueResult();
//            if (room != null) {
//                room.setStatus(status);
//                session.merge(room);
//                logger.info("Room status updated: " + roomNumber + " to " + status);
//            }
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            logger.error("Error updating room status: " + e.getMessage(), e);
//            throw e;
//        }
//    }
//}