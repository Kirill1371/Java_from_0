package repository;

import resources.database.DatabaseConnection;
import model.Room;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RoomRepository {
    public void addRoom(Room room) {
        String query = "INSERT INTO room (id, number, status, price, capacity, stars) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setObject(1, UUID.fromString(room.getId()));
            stmt.setInt(2, room.getNumber());
            stmt.setString(3, room.getStatus());
            stmt.setDouble(4, room.getPrice());
            stmt.setInt(5, room.getCapacity());
            stmt.setInt(6, room.getStars());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Room getRoomByNumber(int number) {
        String query = "SELECT * FROM room WHERE number = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, number);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Room(
                        rs.getString("id"),
                        rs.getInt("number"),
                        rs.getString("status"),
                        rs.getDouble("price"),
                        rs.getInt("capacity"),
                        rs.getInt("stars")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();
        String query = "SELECT * FROM room";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                rooms.add(new Room(
                    rs.getString("id"),
                    rs.getInt("number"),
                    rs.getString("status"),
                    rs.getDouble("price"),
                    rs.getInt("capacity"),
                    rs.getInt("stars")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    public void updateRoomStatus(int number, String status) {
        String query = "UPDATE room SET status = ? WHERE number = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, status);
            stmt.setInt(2, number);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteRoom(int number) {
        String query = "DELETE FROM room WHERE number = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, number);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
