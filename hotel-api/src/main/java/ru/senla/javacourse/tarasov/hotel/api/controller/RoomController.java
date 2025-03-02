package ru.senla.javacourse.tarasov.hotel.api.controller;

import ru.senla.javacourse.tarasov.hotel.api.dto.RoomDto;
import java.util.Date;

public interface RoomController {
    void addRoomToDatabase(RoomDto roomDto);
    void removeRoomFromDatabase(int roomNumber);
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
    //void listLastThreeStays(int roomNumber);
}