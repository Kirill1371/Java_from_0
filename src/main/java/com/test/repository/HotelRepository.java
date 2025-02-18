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
import java.util.List;
import java.util.UUID;

import com.test.annotations.Inject;
import com.test.annotations.Component;


@Component  
public class HotelRepository implements IHotelRepository {

    private final DatabaseConnection databaseConnection;

    @Inject
    public HotelRepository(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public void addRoomToDatabase(int roomNumber, double price, int capacity, int stars) {
        String sql = "INSERT INTO \"Room\" (number, price, capacity, stars, status) VALUES (?, ?, ?, ?, 'Available')";
        try (Connection connection = databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, roomNumber);
            statement.setDouble(2, price);
            statement.setInt(3, capacity);
            statement.setInt(4, stars);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeRoomFromDatabase(int roomNumber) {
        String sql = "DELETE FROM \"Room\" WHERE number = ?";
        try (Connection connection = databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, roomNumber);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Room removed successfully from the database.");
            } else {
                System.out.println("Room with the specified number does not exist.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Room getRoom(int roomNumber) {
        String sql = "SELECT * FROM \"Room\" WHERE number = ?"; 

        try (Connection connection = databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, roomNumber);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Room(
                    resultSet.getString("id"),
                    resultSet.getInt("number"),
                    resultSet.getString("status"),
                    resultSet.getDouble("price"),
                    resultSet.getInt("capacity"),
                    resultSet.getInt("stars")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error while fetching room: " + e.getMessage());
        }

        return null; // Если комната не найдена, возвращаем null
    }



    public Room getRoomFromDatabase(int roomNumber) {
        String sql = "SELECT * FROM \"Room\" WHERE number = ?";
        Room room = null;

        try (Connection connection = databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, roomNumber);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    room = new Room(
                        resultSet.getString("id"),
                        resultSet.getInt("number"),
                        resultSet.getString("status"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("capacity"),
                        resultSet.getInt("stars")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving room details: " + e.getMessage());
            e.printStackTrace();
        }

        return room;
    }


    @Override
    public List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM \"Room\"";

        try (Connection connection = databaseConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                rooms.add(new Room(
                    resultSet.getString("id"),
                    resultSet.getInt("number"),
                    resultSet.getString("status"),
                    resultSet.getDouble("price"),
                    resultSet.getInt("capacity"),
                    resultSet.getInt("stars")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error while fetching rooms: " + e.getMessage());
        }

        return rooms;
    }

    @Override
    public List<Room> getAvailableRooms() {
        List<Room> availableRooms = new ArrayList<>();
        String sql = "SELECT * FROM \"Room\" WHERE \"status\" = 'available'";
    
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
    
            while (resultSet.next()) {
                availableRooms.add(new Room(
                    resultSet.getString("id"),
                    resultSet.getInt("number"),
                    resultSet.getString("status"),
                    resultSet.getDouble("price"),
                    resultSet.getInt("capacity"),
                    resultSet.getInt("stars")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error while fetching available rooms: " + e.getMessage());
        }
    
        return availableRooms;
    }
    
    
    @Override
    public void addService(String guestName, Service service) {
        String sql = "INSERT INTO \"Service\" (id, name, category, price, date, guest_id) " +
                    "VALUES (?, ?, ?, ?, ?, (SELECT id FROM \"Guest\" WHERE name = ?))";

        try (Connection connection = databaseConnection.getConnection()) {
            connection.setAutoCommit(false); // Отключаем автокоммит

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setObject(1, UUID.fromString(service.getId()));
                statement.setString(2, service.getName());
                statement.setString(3, service.getCategory());
                statement.setDouble(4, service.getPrice());
                statement.setTimestamp(5, new java.sql.Timestamp(service.getDate().getTime()));
                statement.setString(6, guestName);

                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    connection.commit(); // Фиксируем транзакцию
                    System.out.println("Service added for guest: " + guestName);
                } else {
                    connection.rollback(); // Откатываем транзакцию, если гость не найден
                    System.out.println("Guest not found: " + guestName);
                }
            } catch (SQLException e) {
                connection.rollback(); // Откатываем транзакцию при ошибке
                System.out.println("Error while adding service for guest: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());
        }
    }

    @Override
    public List<Service> getServicesForGuest(String guestName) {
        List<Service> services = new ArrayList<>();
        String getGuestIdSql = "SELECT id FROM \"Guest\" WHERE name = ?";
        String getServicesSql = "SELECT name, category, price, date FROM \"Service\" WHERE guest_id = ?";
    
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement getGuestIdStmt = connection.prepareStatement(getGuestIdSql);
             PreparedStatement getServicesStmt = connection.prepareStatement(getServicesSql)) {
    
            getGuestIdStmt.setString(1, guestName);
            ResultSet guestResult = getGuestIdStmt.executeQuery();
    
            if (guestResult.next()) {
                UUID guestId = UUID.fromString(guestResult.getString("id"));
    
                getServicesStmt.setObject(1, guestId);
                ResultSet servicesResult = getServicesStmt.executeQuery();
    
                while (servicesResult.next()) {
                    Service service = new Service(
                        servicesResult.getString("name"),
                        servicesResult.getDouble("price"),
                        servicesResult.getString("category")
                    );
                    services.add(service);
                }
            } else {
                System.out.println("Guest not found: " + guestName);
            }
    
        } catch (SQLException e) {
            System.out.println("Error while fetching services for guest: " + e.getMessage());
        }
        return services;
    }
    
    
    @Override
    public Guest getGuest(String name) {
        String sql = "SELECT * FROM \"Guest\" WHERE name = ?";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
    
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Guest guest = new Guest(resultSet.getString("name"));
                    // Загрузим услуги для гостя, передавая имя
                    guest.getServices().addAll(getServicesForGuest(name));
                    return guest;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error while fetching guest: " + e.getMessage());
        }
        return null;
    }
    
    @Override
    public void addStay(Stay stay) {
        String sql = "INSERT INTO \"Stay\" (id, guestid, roomid, checkindate, checkoutdate) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = databaseConnection.getConnection()) {
            connection.setAutoCommit(false); // Отключаем автокоммит

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setObject(1, UUID.fromString(stay.getId()));
                statement.setObject(2, UUID.fromString(stay.getGuest().getId()));
                statement.setObject(3, UUID.fromString(stay.getRoom().getId()));
                statement.setTimestamp(4, new java.sql.Timestamp(stay.getCheckInDate().getTime()));
                statement.setTimestamp(5, new java.sql.Timestamp(stay.getCheckOutDate().getTime()));

                statement.executeUpdate();
                connection.commit(); // Фиксируем транзакцию
                System.out.println("Stay added successfully!");
            } catch (SQLException e) {
                connection.rollback(); // Откатываем транзакцию при ошибке
                System.out.println("Error while adding stay: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());
        }
    }

    @Override
    public Room getRoomById(UUID id) {
        String sql = "SELECT * FROM \"Room\" WHERE id = ?";
        try (Connection connection = databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setObject(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Room(
                    resultSet.getString("id"),
                    resultSet.getInt("number"),
                    resultSet.getString("status"),
                    resultSet.getDouble("price"),
                    resultSet.getInt("capacity"),
                    resultSet.getInt("stars")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error while fetching room: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Stay> getAllStays() {
        List<Stay> stays = new ArrayList<>();
        String sql = "SELECT g.name AS guest_name, r.id AS room_id, r.number AS room_number, r.status AS room_status, " +
                     "r.price AS room_price, r.capacity AS room_capacity, r.stars AS room_stars, " +
                     "s.check_in_date, s.check_out_date " +
                     "FROM \"Stay\" s " +
                     "JOIN \"Guest\" g ON s.guest_id = g.id " +
                     "JOIN \"Room\" r ON s.room_id = r.id";
    
        try (Connection connection = databaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
    
            while (resultSet.next()) {
                Guest guest = new Guest(resultSet.getString("guest_name"));
    
                Room room = new Room(
                    resultSet.getString("room_id"),   
                    resultSet.getInt("room_number"),
                    resultSet.getString("room_status"),
                    resultSet.getDouble("room_price"),
                    resultSet.getInt("room_capacity"),
                    resultSet.getInt("room_stars")
                );
    
                // Создаём пребывание (Stay)
                Stay stay = new Stay(
                    guest,
                    room,
                    resultSet.getDate("check_in_date"),
                    resultSet.getDate("check_out_date")
                );
    
                stays.add(stay);
            }
        } catch (SQLException e) {
            System.out.println("Error while fetching stays: " + e.getMessage());
        }
    
        return stays;
    }

    @Override
    public List<Guest> getAllGuests() {
        List<Guest> guests = new ArrayList<>();
        String sql = "SELECT name FROM \"Guest\"";
    
        try (Connection connection = databaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
    
            while (resultSet.next()) {
                guests.add(new Guest(resultSet.getString("name")));
            }
        } catch (SQLException e) {
            System.out.println("Error while fetching guests: " + e.getMessage());
        }
    
        return guests;
    }
    
    
    @Override
    public void updateRoomStatus(int roomNumber, String newStatus) {
        String sql = "UPDATE \"Room\" SET status = ? WHERE number = ?";
    
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
    
            statement.setString(1, newStatus);
            statement.setInt(2, roomNumber);
    
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Room status updated successfully!");
            } else {
                System.out.println("No room found with number: " + roomNumber);
            }
        } catch (SQLException e) {
            System.out.println("Error while updating room status: " + e.getMessage());
        }
    }
    

    @Override
    public void setStatusAv(int roomNumber) {
        String sql = "UPDATE \"Room\" SET status = 'Available' WHERE number = ?"; 

        try (Connection connection = databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, roomNumber); 
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Room " + roomNumber + " status updated to Available.");
            } else {
                System.out.println("Room not found or already checked out.");
            }
        } catch (SQLException e) {
            System.out.println("Error while updating room status: " + e.getMessage());
        }
    }
}
