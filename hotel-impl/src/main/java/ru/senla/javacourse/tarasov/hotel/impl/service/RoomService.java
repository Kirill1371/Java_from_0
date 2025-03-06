package ru.senla.javacourse.tarasov.hotel.impl.service;

import java.util.Date;
import java.util.List;
import ru.senla.javacourse.tarasov.hotel.api.dto.RoomDto;

public interface RoomService {
    void addRoom(RoomDto roomDto);
    void removeRoom(int roomNumber);
    void setRoomStatus(int roomNumber, String status);
    void setRoomPrice(int roomNumber, double price);
    List<RoomDto> getAllRooms();
    List<RoomDto> getAvailableRooms();
    List<RoomDto> getRoomsSortedByPrice();
    List<RoomDto> getRoomsSortedByCapacity();
    List<RoomDto> getRoomsSortedByStars();
    List<RoomDto> getAvailableRoomsSortedByPrice();
    List<RoomDto> getAvailableRoomsSortedByCapacity();
    List<RoomDto> getAvailableRoomsSortedByStars();
    int getTotalAvailableRooms();
    List<RoomDto> getRoomsAvailableByDate(Date date);
    RoomDto getRoomDetails(int roomNumber);
}