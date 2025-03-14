package ru.senla.javacourse.tarasov.hotel.impl.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.senla.javacourse.tarasov.hotel.api.controller.RoomController;
import ru.senla.javacourse.tarasov.hotel.api.dto.RoomDto;
import ru.senla.javacourse.tarasov.hotel.db.entity.Room;
import ru.senla.javacourse.tarasov.hotel.impl.mapper.RoomMapper;
import ru.senla.javacourse.tarasov.hotel.impl.service.RoomService;
import ru.senla.javacourse.tarasov.hotel.ioc.annotations.Component;
import ru.senla.javacourse.tarasov.hotel.ioc.annotations.Inject;

@Controller
public class RoomControllerImpl implements RoomController {

    private RoomService roomService;

    @Autowired
    public RoomControllerImpl(RoomService roomService) {
        this.roomService = roomService;
    }

    @Override
    public void addRoom(RoomDto roomDto) {
        Room room = RoomMapper.toEntity(roomDto);
        roomService.addRoom(roomDto);
    }

    @Override
    public void removeRoom(int roomNumber) {
        roomService.removeRoom(roomNumber);
    }

    @Override
    public void setRoomStatus(int roomNumber, String status) {
        roomService.setRoomStatus(roomNumber, status);
    }

    @Override
    public void setRoomPrice(int roomNumber, double price) {
        roomService.setRoomPrice(roomNumber, price);
    }

    @Override
    public void listAllRooms() {
        List<RoomDto> rooms = roomService.getAllRooms(); // Убрали повторный вызов маппера
        rooms.forEach(room ->
                System.out.println("Room: " + room.getNumber() + ", Status: " + room.getStatus() + ", Price: " + room.getPrice()));
    }


    @Override
    public void listAvailableRooms() {
        List<RoomDto> availableRooms = roomService.getAvailableRooms();
        availableRooms.forEach(room -> System.out.println("Room: " + room.getNumber() + ", Status: " + room.getStatus() + ", Price: " + room.getPrice()));
    }

    @Override
    public void listRoomsSortedByPrice() {
        List<RoomDto> rooms = roomService.getRoomsSortedByPrice();
        rooms.forEach(room -> System.out.println("Room: " + room.getNumber() + ", Price: " + room.getPrice()));
    }

    @Override
    public void listRoomsSortedByCapacity() {
        List<RoomDto> rooms = roomService.getRoomsSortedByCapacity();
        rooms.forEach(room -> System.out.println("Room: " + room.getNumber() + ", Capacity: " + room.getCapacity()));
    }

    @Override
    public void listRoomsSortedByStars() {
        List<RoomDto> rooms = roomService.getRoomsSortedByStars();
        rooms.forEach(room -> System.out.println("Room: " + room.getNumber() + ", Stars: " + room.getStars()));
    }

    @Override
    public void listAvailableRoomsSortedByPrice() {
        List<RoomDto> rooms = roomService.getAvailableRoomsSortedByPrice();
        rooms.forEach(room -> System.out.println("Room: " + room.getNumber() + ", Price: " + room.getPrice()));
    }

    @Override
    public void listAvailableRoomsSortedByCapacity() {
        List<RoomDto> rooms = roomService.getAvailableRoomsSortedByCapacity();
        rooms.forEach(room -> System.out.println("Room: " + room.getNumber() + ", Capacity: " + room.getCapacity()));
    }

    @Override
    public void listAvailableRoomsSortedByStars() {
        List<RoomDto> rooms = roomService.getAvailableRoomsSortedByStars();
        rooms.forEach(room -> System.out.println("Room: " + room.getNumber() + ", Stars: " + room.getStars()));
    }

    @Override
    public void getTotalAvailableRooms() {
        int totalAvailableRooms = roomService.getTotalAvailableRooms();
        System.out.println("Total available rooms: " + totalAvailableRooms);
    }

    @Override
    public void listRoomsAvailableByDate(Date date) {
        List<RoomDto> rooms = roomService.getRoomsAvailableByDate(date);
        rooms.forEach(room -> System.out.println("Room: " + room.getNumber() + ", Status: " + room.getStatus() + ", Price: " + room.getPrice()));
    }

    @Override
    public void getRoomDetails(int roomNumber) {
        RoomDto roomDto = roomService.getRoomDetails(roomNumber);
        System.out.println("Room: " + roomDto.getNumber() + ", Status: " + roomDto.getStatus() + ", Price: " + roomDto.getPrice() + ", Capacity: " + roomDto.getCapacity() + ", Stars: " + roomDto.getStars());
    }
}