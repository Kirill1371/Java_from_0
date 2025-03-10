package ru.senla.javacourse.tarasov.hotel.impl.repository;

import ru.senla.javacourse.tarasov.hotel.db.entity.Room;

import java.util.List;
import java.util.UUID;

public interface RoomRepository {
    void addRoom(Room room);
    Room getRoom(int roomNumber);
    List<Room> getAllRooms();
    List<Room> getAvailableRooms();
    void setStatusAv(int roomNumber);
    Room getRoomById(UUID id);
    void updateRoomStatus(int roomNumber, String newStatus);
    void removeRoom(int roomNumber);
}
