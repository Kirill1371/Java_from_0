//package ru.senla.javacourse.tarasov.hotel.impl.controller;
//
//import java.util.Date;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import ru.senla.javacourse.tarasov.hotel.api.controller.RoomController;
//import ru.senla.javacourse.tarasov.hotel.api.dto.RoomDto;
//import ru.senla.javacourse.tarasov.hotel.db.entity.Room;
//import ru.senla.javacourse.tarasov.hotel.impl.mapper.RoomMapper;
//import ru.senla.javacourse.tarasov.hotel.impl.service.RoomService;
//import ru.senla.javacourse.tarasov.hotel.ioc.annotations.Component;
//import ru.senla.javacourse.tarasov.hotel.ioc.annotations.Inject;
//
//@Controller
//public class RoomControllerImpl implements RoomController {
//
//    private RoomService roomService;
//
//    @Autowired
//    public RoomControllerImpl(RoomService roomService) {
//        this.roomService = roomService;
//    }
//
//    @Override
//    public void addRoom(RoomDto roomDto) {
//        Room room = RoomMapper.toEntity(roomDto);
//        roomService.addRoom(roomDto);
//    }
//
//    @Override
//    public void removeRoom(int roomNumber) {
//        roomService.removeRoom(roomNumber);
//    }
//
//    @Override
//    public void setRoomStatus(int roomNumber, String status) {
//        roomService.setRoomStatus(roomNumber, status);
//    }
//
//    @Override
//    public void setRoomPrice(int roomNumber, double price) {
//        roomService.setRoomPrice(roomNumber, price);
//    }
//
//    @Override
//    public void listAllRooms() {
//        List<RoomDto> rooms = roomService.getAllRooms(); // Убрали повторный вызов маппера
//        rooms.forEach(room ->
//                System.out.println("Room: " + room.getNumber() + ", Status: " + room.getStatus() + ", Price: " + room.getPrice()));
//    }
//
//
//    @Override
//    public void listAvailableRooms() {
//        List<RoomDto> availableRooms = roomService.getAvailableRooms();
//        availableRooms.forEach(room -> System.out.println("Room: " + room.getNumber() + ", Status: " + room.getStatus() + ", Price: " + room.getPrice()));
//    }
//
//    @Override
//    public void listRoomsSortedByPrice() {
//        List<RoomDto> rooms = roomService.getRoomsSortedByPrice();
//        rooms.forEach(room -> System.out.println("Room: " + room.getNumber() + ", Price: " + room.getPrice()));
//    }
//
//    @Override
//    public void listRoomsSortedByCapacity() {
//        List<RoomDto> rooms = roomService.getRoomsSortedByCapacity();
//        rooms.forEach(room -> System.out.println("Room: " + room.getNumber() + ", Capacity: " + room.getCapacity()));
//    }
//
//    @Override
//    public void listRoomsSortedByStars() {
//        List<RoomDto> rooms = roomService.getRoomsSortedByStars();
//        rooms.forEach(room -> System.out.println("Room: " + room.getNumber() + ", Stars: " + room.getStars()));
//    }
//
//    @Override
//    public void listAvailableRoomsSortedByPrice() {
//        List<RoomDto> rooms = roomService.getAvailableRoomsSortedByPrice();
//        rooms.forEach(room -> System.out.println("Room: " + room.getNumber() + ", Price: " + room.getPrice()));
//    }
//
//    @Override
//    public void listAvailableRoomsSortedByCapacity() {
//        List<RoomDto> rooms = roomService.getAvailableRoomsSortedByCapacity();
//        rooms.forEach(room -> System.out.println("Room: " + room.getNumber() + ", Capacity: " + room.getCapacity()));
//    }
//
//    @Override
//    public void listAvailableRoomsSortedByStars() {
//        List<RoomDto> rooms = roomService.getAvailableRoomsSortedByStars();
//        rooms.forEach(room -> System.out.println("Room: " + room.getNumber() + ", Stars: " + room.getStars()));
//    }
//
//    @Override
//    public void getTotalAvailableRooms() {
//        int totalAvailableRooms = roomService.getTotalAvailableRooms();
//        System.out.println("Total available rooms: " + totalAvailableRooms);
//    }
//
//    @Override
//    public void listRoomsAvailableByDate(Date date) {
//        List<RoomDto> rooms = roomService.getRoomsAvailableByDate(date);
//        rooms.forEach(room -> System.out.println("Room: " + room.getNumber() + ", Status: " + room.getStatus() + ", Price: " + room.getPrice()));
//    }
//
//    @Override
//    public void getRoomDetails(int roomNumber) {
//        RoomDto roomDto = roomService.getRoomDetails(roomNumber);
//        System.out.println("Room: " + roomDto.getNumber() + ", Status: " + roomDto.getStatus() + ", Price: " + roomDto.getPrice() + ", Capacity: " + roomDto.getCapacity() + ", Stars: " + roomDto.getStars());
//    }
//}


package ru.senla.javacourse.tarasov.hotel.impl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.senla.javacourse.tarasov.hotel.api.controller.RoomController;
import ru.senla.javacourse.tarasov.hotel.api.dto.RoomDto;
import ru.senla.javacourse.tarasov.hotel.impl.service.RoomService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomControllerImpl implements RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomControllerImpl(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping
    public ResponseEntity<Void> addRoom(@RequestBody RoomDto roomDto) {
        roomService.addRoom(roomDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{roomNumber}")
    public ResponseEntity<Void> removeRoom(@PathVariable int roomNumber) {
        roomService.removeRoom(roomNumber);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{roomNumber}/status")
    public ResponseEntity<Void> setRoomStatus(@PathVariable int roomNumber, @RequestParam String status) {
        roomService.setRoomStatus(roomNumber, status);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{roomNumber}/price")
    public ResponseEntity<Void> setRoomPrice(@PathVariable int roomNumber, @RequestParam double price) {
        roomService.setRoomPrice(roomNumber, price);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<RoomDto>> listAllRooms() {
        List<RoomDto> rooms = roomService.getAllRooms();
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    @GetMapping("/available")
    public ResponseEntity<List<RoomDto>> listAvailableRooms() {
        List<RoomDto> availableRooms = roomService.getAvailableRooms();
        return new ResponseEntity<>(availableRooms, HttpStatus.OK);
    }

    @GetMapping("/sorted/price")
    public ResponseEntity<List<RoomDto>> listRoomsSortedByPrice() {
        List<RoomDto> rooms = roomService.getRoomsSortedByPrice();
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    @GetMapping("/sorted/capacity")
    public ResponseEntity<List<RoomDto>> listRoomsSortedByCapacity() {
        List<RoomDto> rooms = roomService.getRoomsSortedByCapacity();
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    @GetMapping("/sorted/stars")
    public ResponseEntity<List<RoomDto>> listRoomsSortedByStars() {
        List<RoomDto> rooms = roomService.getRoomsSortedByStars();
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    @GetMapping("/available/sorted/price")
    public ResponseEntity<List<RoomDto>> listAvailableRoomsSortedByPrice() {
        List<RoomDto> rooms = roomService.getAvailableRoomsSortedByPrice();
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    @GetMapping("/available/sorted/capacity")
    public ResponseEntity<List<RoomDto>> listAvailableRoomsSortedByCapacity() {
        List<RoomDto> rooms = roomService.getAvailableRoomsSortedByCapacity();
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    @GetMapping("/available/sorted/stars")
    public ResponseEntity<List<RoomDto>> listAvailableRoomsSortedByStars() {
        List<RoomDto> rooms = roomService.getAvailableRoomsSortedByStars();
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    @GetMapping("/available/date")
    public ResponseEntity<List<RoomDto>> listRoomsAvailableByDate(@RequestParam Date date) {
        List<RoomDto> rooms = roomService.getRoomsAvailableByDate(date);
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    @GetMapping("/{roomNumber}")
    public ResponseEntity<RoomDto> getRoomDetails(@PathVariable int roomNumber) {
        RoomDto roomDto = roomService.getRoomDetails(roomNumber);
        return new ResponseEntity<>(roomDto, HttpStatus.OK);
    }

    @GetMapping("/total-available")
    public ResponseEntity<Integer> getTotalAvailableRooms() {
        int totalAvailableRooms = roomService.getTotalAvailableRooms();
        return new ResponseEntity<>(totalAvailableRooms, HttpStatus.OK);
    }
}