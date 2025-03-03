package ru.senla.javacourse.tarasov.hotel.api.controller;

import java.util.Date;
import ru.senla.javacourse.tarasov.hotel.api.dto.RoomDto;

public interface RoomController {

    // todo зачем в названии метода toDatabase? Это ещё можно было бы понять работай мы
    //  с каким-то классом который точно подключается к БД. А учитывая что котнроллер даже не с
    //  репозиторием работает, а лишь направляет запросы в севисы, toDatabase избыточно. Просто
    //  addRoom
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
}