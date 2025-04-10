package controller;

import model.Room;
import model.Service;
import service.IHotelService;

public class HotelController {
    private IHotelService hotelService;

    public HotelController(IHotelService hotelService) {
        this.hotelService = hotelService;
    }

    public void addRoom(Room room) {
        hotelService.addRoom(room);
    }

    public void removeRoom(int roomNumber) {
        hotelService.removeRoom(roomNumber);
    }

    public void checkIn(int roomNumber) {
        hotelService.checkIn(roomNumber);
    }

    public void checkOut(int roomNumber) {
        hotelService.checkOut(roomNumber);
    }

    public void setRoomStatus(int roomNumber, String status) {
        hotelService.setRoomStatus(roomNumber, status);
    }

    public void setRoomPrice(int roomNumber, double price) {
        hotelService.setRoomPrice(roomNumber, price);
    }

    public void addService(Service service) {
        hotelService.addService(service);
    }

    public void listAllRooms() {
        for (Room room : hotelService.getAllRooms()) {
            System.out.println("Room: " + room.getNumber() + ", Status: " + room.getStatus() + ", Price: " + room.getPrice());
        }
    }

    public void listAllServices() {
        for (Service service : hotelService.getAllServices()) {
            System.out.println("Service: " + service.getName() + ", Price: " + service.getPrice());
        }
    }
}
