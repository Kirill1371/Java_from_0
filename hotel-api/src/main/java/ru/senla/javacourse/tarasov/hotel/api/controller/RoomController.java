package ru.senla.javacourse.tarasov.hotel.api.controller;

import java.util.Date;
import ru.senla.javacourse.tarasov.hotel.api.dto.RoomDto;

public interface RoomController {

    void addRoom(RoomDto roomDto);
    void removeRoom(int roomNumber);
    void setRoomStatus(int roomNumber, String status);
    void setRoomPrice(int roomNumber, double price);
    void listAllRooms();
    void listAvailableRooms();
    void listRoomsSortedByPrice();
    void listRoomsSortedByCapacity();
    void listRoomsSortedByStars();
    void listAvailableRoomsSortedByPrice();
    void listAvailableRoomsSortedByCapacity();
    void listAvailableRoomsSortedByStars();
    void getTotalAvailableRooms();
    void listRoomsAvailableByDate(Date date);
    void getRoomDetails(int roomNumber);
}