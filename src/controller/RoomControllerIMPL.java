package controller;

import java.util.Date;

import model.Room;
import model.Stay;
import service.IHotelService;

public class RoomControllerIMPL implements HotelController{
    private IHotelService hotelService;


    public RoomControllerIMPL(IHotelService hotelService) {
        this.hotelService = hotelService;
    }

    public void addRoom(Room room) {
        hotelService.addRoom(room);
    }

    public void removeRoom(int roomNumber) {
        hotelService.removeRoom(roomNumber);
    }

    public void setRoomStatus(int roomNumber, String status) {
        hotelService.setRoomStatus(roomNumber, status);
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
        for (Room room : hotelService.getAvailableRooms()) {
            System.out.println("Room: " + room.getNumber() + ", Status: " + room.getStatus() + ", Price: " + room.getPrice());
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

    public void listLastThreeStays(int roomNumber) {
        for (Stay stay : hotelService.getLastThreeStays(roomNumber)) {
            System.out.println("Room: " + stay.getRoom().getNumber() + ", Check-in: " + stay.getCheckInDate() + ", Check-out: " + stay.getCheckOutDate());
        }
    }

    public void importRooms(String filePath) {
        hotelService.importRoomsFromCSV(filePath);
    }
    
    public void exportRooms(String filePath) {
        hotelService.exportRoomsToCSV(filePath);
    }
}


