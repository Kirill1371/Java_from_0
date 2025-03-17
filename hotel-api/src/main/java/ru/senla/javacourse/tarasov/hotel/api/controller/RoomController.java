package ru.senla.javacourse.tarasov.hotel.api.controller;

import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import ru.senla.javacourse.tarasov.hotel.api.dto.RoomDto;

public interface RoomController {

    ResponseEntity<Void> addRoom(RoomDto roomDto);
    ResponseEntity<Void> removeRoom(int roomNumber);
    ResponseEntity<Void> setRoomStatus(int roomNumber, String status);
    ResponseEntity<Void> setRoomPrice(int roomNumber, double price);
    ResponseEntity<List<RoomDto>> listAllRooms();
    ResponseEntity<List<RoomDto>> listAvailableRooms();
    ResponseEntity<List<RoomDto>> listRoomsSortedByPrice();
    ResponseEntity<List<RoomDto>> listRoomsSortedByCapacity();
    ResponseEntity<List<RoomDto>> listRoomsSortedByStars();
    ResponseEntity<List<RoomDto>> listAvailableRoomsSortedByPrice();
    ResponseEntity<List<RoomDto>> listAvailableRoomsSortedByCapacity();
    ResponseEntity<List<RoomDto>> listAvailableRoomsSortedByStars();
    ResponseEntity<Integer> getTotalAvailableRooms();
    ResponseEntity<List<RoomDto>> listRoomsAvailableByDate(Date date);
    ResponseEntity<RoomDto> getRoomDetails(int roomNumber);
}