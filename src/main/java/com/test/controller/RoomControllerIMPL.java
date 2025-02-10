package com.test.controller;

import java.util.Date;
import java.util.List;

import com.test.annotations.Inject;

import com.test.annotations.Component;
import com.test.model.Room;
import com.test.model.Stay;
import com.test.service.HotelService;

@Component
public class RoomControllerIMPL implements RoomController {
    @Inject
    private HotelService hotelService;

    @Inject
    public RoomControllerIMPL(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    public void addRoomToDatabase(int roomNumber, double price, int capacity, int stars) {
        hotelService.addRoomToDatabase(roomNumber, price, capacity, stars);
    }

    public void removeRoomFromDatabase(int roomNumber) {
        hotelService.removeRoomFromDatabase(roomNumber);
    }

    public void setRoomStatus(int roomNumber, String status) {
        boolean isStatusChangeEnabled = com.test.utils.ConfigManager.getBooleanProperty("room.status.change.enabled", false);

        if (!isStatusChangeEnabled) {
            System.out.println("Changing room status is disabled by configuration.");
            return;
        }

        hotelService.setRoomStatus(roomNumber, status);
        System.out.println("Room status updated to: " + status);
    }

    public void setRoomPrice(int roomNumber, double price) {
        hotelService.setRoomPrice(roomNumber, price);
    }

    public void listAllRooms() {
        for (Room room : hotelService.getAllRooms()) {
            System.out.println("Room: " + room.getNumber() + ", Status: " + room.getStatus() + ", Price: " + room.getPrice() + ", Id: " + room.getId());
        }
    }

    public void listAvailableRooms() {
        List<Room> availableRooms = hotelService.getAvailableRooms();
        if (availableRooms.isEmpty()) {
            System.out.println("No available rooms found.");
        } else {
            for (Room room : availableRooms) {
                System.out.println("Room: " + room.getNumber() +
                                ", Status: " + room.getStatus() +
                                ", Price: " + room.getPrice());
            }
        }
    }


    
    public void listRoomsSortedByPrice() {
        for (Room room : hotelService.getRoomsSortedByPrice()) {
            System.out.println("Room: " + room.getNumber() + ", Price: " + room.getPrice());
        }
    }

    public void listRoomsSortedByCapacity() {
        for (Room room : hotelService.getRoomsSortedByCapacity()) {
            System.out.println("Room: " + room.getNumber() + ", Capacity: " + room.getCapacity());
        }
    }

    public void listRoomsSortedByStars() {
        for (Room room : hotelService.getRoomsSortedByStars()) {
            System.out.println("Room: " + room.getNumber() + ", Stars: " + room.getStars());
        }
    }

    public void listAvailableRoomsSortedByPrice() {
        for (Room room : hotelService.getAvailableRoomsSortedByPrice()) {
            System.out.println("Room: " + room.getNumber() + ", Price: " + room.getPrice());
        }
    }

    public void listAvailableRoomsSortedByCapacity() {
        for (Room room : hotelService.getAvailableRoomsSortedByCapacity()) {
            System.out.println("Room: " + room.getNumber() + ", Capacity: " + room.getCapacity());
        }
    }

    public void listAvailableRoomsSortedByStars() {
        for (Room room : hotelService.getAvailableRoomsSortedByStars()) {
            System.out.println("Room: " + room.getNumber() + ", Stars: " + room.getStars());
        }
    }
   
    public void getTotalAvailableRooms() {
        int totalAvailableRooms = hotelService.getTotalAvailableRooms();
        System.out.println("Total available rooms: " + totalAvailableRooms);
    }

    public void listRoomsAvailableByDate(Date date) {
        for (Room room : hotelService.getRoomsAvailableByDate(date)) {
            System.out.println("Room: " + room.getNumber() + ", Status: " + room.getStatus() + ", Price: " + room.getPrice());
        }
    }


    public void getRoomDetails(int roomNumber) {
        Room room = hotelService.getRoomDetails(roomNumber);
        if (room != null) {
            System.out.println("Room: " + room.getNumber() + ", Status: " + room.getStatus() + ", Price: " + room.getPrice() + ", Capacity: " + room.getCapacity() + ", Stars: " + room.getStars());
        } else {
            System.out.println("Room not found: " + roomNumber);
        }
    }


    public void getRoomDetailsFromDB(int roomNumber) {
        Room room = hotelService.getRoomDetails(roomNumber);
    
        if (room != null) {
            // Выводим информацию о комнате
            System.out.println("Room Details:");
            System.out.println("Number: " + room.getNumber());
            System.out.println("Price: $" + room.getPrice());
            System.out.println("Capacity: " + room.getCapacity() + " people");
            System.out.println("Stars: " + room.getStars() + " stars");
            System.out.println("Status: " + room.getStatus());
        } else {
            System.out.println("No room found with number: " + roomNumber);
        }
    }
    


    public void listLastThreeStays(int roomNumber) {
        for (Stay stay : hotelService.getLastThreeStays(roomNumber)) {
            System.out.println("Room: " + stay.getRoom().getNumber() + ", Check-in: " + stay.getCheckInDate() + ", Check-out: " + stay.getCheckOutDate());
        }
    }
}


