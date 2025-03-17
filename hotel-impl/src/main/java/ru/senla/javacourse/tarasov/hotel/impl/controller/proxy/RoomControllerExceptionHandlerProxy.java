//package ru.senla.javacourse.tarasov.hotel.impl.controller.proxy;
//
//import java.util.Date;
//import ru.senla.javacourse.tarasov.hotel.api.controller.RoomController;
//import ru.senla.javacourse.tarasov.hotel.api.dto.RoomDto;
//
//public class RoomControllerExceptionHandlerProxy implements RoomController {
//    private final RoomController roomController;
//
//    public RoomControllerExceptionHandlerProxy(RoomController roomController) {
//        this.roomController = roomController;
//    }
//
//    public void addRoom(RoomDto roomDto) {
//        try {
//            roomController.addRoom(roomDto);
//        } catch(Exception e) {
//            System.out.println("404");
//        }
//
//    }
//
//    public void removeRoom(int roomNumber) {
//        try {
//            roomController.removeRoom(roomNumber);
//        } catch(Exception e) {
//            System.out.println("404");
//        }
//    }
//
//    public void setRoomStatus(int roomNumber, String status) {
//        try {
//            roomController.setRoomStatus(roomNumber, status);
//        }
//        catch (Exception e) {
//            System.out.println("404");
//        }
//    }
//
//    public void setRoomPrice(int roomNumber, double price) {
//        try {
//            roomController.setRoomPrice(roomNumber, price);
//        } catch (Exception e) {
//            System.out.println("404");
//        }
//    }
//
//    public void listAllRooms() {
//        try {
//            roomController.listAllRooms();
//        } catch (Exception e) {
//            System.out.println("404");
//        }
//    }
//
//    public void listAvailableRooms() {
//        try {
//            roomController.listAvailableRooms();
//        } catch (Exception e) {
//            System.out.println("404");
//        }
//    }
//
//    public void listRoomsSortedByPrice() {
//        try {
//            roomController.listRoomsSortedByPrice();
//        } catch (Exception e) {
//            System.out.println("404");
//        }
//    }
//
//    public void listRoomsSortedByCapacity() {
//        try {
//            roomController.listRoomsSortedByCapacity();
//        } catch (Exception e) {
//            System.out.println("404");
//        }
//    }
//
//    public void listRoomsSortedByStars() {
//        try {
//            roomController.listRoomsSortedByStars();
//        } catch (Exception e) {
//            System.out.println("404");
//        }
//    }
//
//    public void listAvailableRoomsSortedByPrice() {
//        try {
//            roomController.listAvailableRoomsSortedByPrice();
//        } catch (Exception e) {
//            System.out.println("404");
//        }
//    }
//
//    public void listAvailableRoomsSortedByCapacity() {
//        try {
//            roomController.listAvailableRoomsSortedByCapacity();
//        } catch (Exception e) {
//            System.out.println("404");
//        }
//    }
//
//    public void listAvailableRoomsSortedByStars() {
//        try {
//            roomController.listAvailableRoomsSortedByStars();
//        } catch (Exception e) {
//            System.out.println("404");
//        }
//    }
//
//    public void listRoomsAvailableByDate(Date date) {
//        try {
//            roomController.listRoomsAvailableByDate(date);
//        } catch (Exception e) {
//            System.out.println("404");
//        }
//    }
//
//    public void getRoomDetails(int roomNumber) {
//        try {
//            roomController.getRoomDetails(roomNumber);
//        } catch (Exception e) {
//            System.out.println("404");
//        }
//    }
//
//    public void getRoomDetailsFromDB(int roomNumber) {
//        try {
//            roomController.getRoomDetails(roomNumber);
//        } catch (Exception e) {
//            System.out.println("404");
//        }
//    }
//
//    public void getTotalAvailableRooms() {
//        try {
//            roomController.getTotalAvailableRooms();
//        } catch (Exception e) {
//            System.out.println("404");
//        }
//    }
//}


package ru.senla.javacourse.tarasov.hotel.impl.controller.proxy;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.senla.javacourse.tarasov.hotel.api.controller.RoomController;
import ru.senla.javacourse.tarasov.hotel.api.dto.RoomDto;

import java.util.Date;
import java.util.List;

public class RoomControllerExceptionHandlerProxy implements RoomController {

    private final RoomController roomController;

    public RoomControllerExceptionHandlerProxy(RoomController roomController) {
        this.roomController = roomController;
    }

    @Override
    public ResponseEntity<Void> addRoom(RoomDto roomDto) {
        try {
            return roomController.addRoom(roomDto);
        } catch (Exception e) {
            System.out.println("404");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<Void> removeRoom(int roomNumber) {
        try {
            return roomController.removeRoom(roomNumber);
        } catch (Exception e) {
            System.out.println("404");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<Void> setRoomStatus(int roomNumber, String status) {
        try {
            return roomController.setRoomStatus(roomNumber, status);
        } catch (Exception e) {
            System.out.println("404");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<Void> setRoomPrice(int roomNumber, double price) {
        try {
            return roomController.setRoomPrice(roomNumber, price);
        } catch (Exception e) {
            System.out.println("404");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<List<RoomDto>> listAllRooms() {
        try {
            return roomController.listAllRooms();
        } catch (Exception e) {
            System.out.println("404");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<List<RoomDto>> listAvailableRooms() {
        try {
            return roomController.listAvailableRooms();
        } catch (Exception e) {
            System.out.println("404");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<List<RoomDto>> listRoomsSortedByPrice() {
        try {
            return roomController.listRoomsSortedByPrice();
        } catch (Exception e) {
            System.out.println("404");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<List<RoomDto>> listRoomsSortedByCapacity() {
        try {
            return roomController.listRoomsSortedByCapacity();
        } catch (Exception e) {
            System.out.println("404");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<List<RoomDto>> listRoomsSortedByStars() {
        try {
            return roomController.listRoomsSortedByStars();
        } catch (Exception e) {
            System.out.println("404");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<List<RoomDto>> listAvailableRoomsSortedByPrice() {
        try {
            return roomController.listAvailableRoomsSortedByPrice();
        } catch (Exception e) {
            System.out.println("404");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<List<RoomDto>> listAvailableRoomsSortedByCapacity() {
        try {
            return roomController.listAvailableRoomsSortedByCapacity();
        } catch (Exception e) {
            System.out.println("404");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<List<RoomDto>> listAvailableRoomsSortedByStars() {
        try {
            return roomController.listAvailableRoomsSortedByStars();
        } catch (Exception e) {
            System.out.println("404");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<List<RoomDto>> listRoomsAvailableByDate(Date date) {
        try {
            return roomController.listRoomsAvailableByDate(date);
        } catch (Exception e) {
            System.out.println("404");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<RoomDto> getRoomDetails(int roomNumber) {
        try {
            return roomController.getRoomDetails(roomNumber);
        } catch (Exception e) {
            System.out.println("404");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<Integer> getTotalAvailableRooms() {
        try {
            return roomController.getTotalAvailableRooms();
        } catch (Exception e) {
            System.out.println("404");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}