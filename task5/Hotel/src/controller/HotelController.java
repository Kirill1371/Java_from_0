package controller;

import model.Room;
import model.Service;
import model.Guest;
import model.Stay;
import service.IHotelService;
import java.util.Date;
import java.util.List;

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

    public void checkIn(int roomNumber, Guest guest, Date checkInDate, Date checkOutDate) {
        hotelService.checkIn(roomNumber, guest, checkInDate, checkOutDate);
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

    public void listAvailableRooms() {
        for (Room room : hotelService.getAvailableRooms()) {
            System.out.println("Room: " + room.getNumber() + ", Status: " + room.getStatus() + ", Price: " + room.getPrice());
        }
    }

    public void listAllGuests() {
        for (Guest guest : hotelService.getAllGuests()) {
            System.out.println("Guest: " + guest.getName());
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

    public void listGuestsSortedByName() {
        for (Guest guest : hotelService.getGuestsSortedByName()) {
            System.out.println("Guest: " + guest.getName());
        }
    }

    public void listGuestsSortedByCheckOutDate() {
        for (Guest guest : hotelService.getGuestsSortedByCheckOutDate()) {
            System.out.println("Guest: " + guest.getName());
        }
    }

    public void getTotalAvailableRooms() {
        int totalAvailableRooms = hotelService.getTotalAvailableRooms();
        System.out.println("Total available rooms: " + totalAvailableRooms);
    }

    public void getTotalGuests() {
        int totalGuests = hotelService.getTotalGuests();
        System.out.println("Total guests: " + totalGuests);
    }
    public void listRoomsAvailableByDate(Date date) {
        for (Room room : hotelService.getRoomsAvailableByDate(date)) {
            System.out.println("Room: " + room.getNumber() + ", Status: " + room.getStatus() + ", Price: " + room.getPrice());
        }
    }

    public void getTotalPaymentForGuest(String guestName) {
        double totalPayment = hotelService.getTotalPaymentForGuest(guestName);
        System.out.println("Total payment for guest " + guestName + ": " + totalPayment);
    }

    public void listLastThreeStays(int roomNumber) {
        for (Stay stay : hotelService.getLastThreeStays(roomNumber)) {
            System.out.println("Room: " + stay.getRoom().getNumber() + ", Check-in: " + stay.getCheckInDate() + ", Check-out: " + stay.getCheckOutDate());
        }
    }

    public void listGuestServicesSortedByPrice(String guestName) {
        for (Service service : hotelService.getGuestServicesSortedByPrice(guestName)) {
            System.out.println("Service: " + service.getName() + ", Price: " + service.getPrice());
        }
    }

    public void listGuestServicesSortedByDate(String guestName) {
        for (Service service : hotelService.getGuestServicesSortedByDate(guestName)) {
            System.out.println("Service: " + service.getName() + ", Date: " + service.getDate());
        }
    }

    public void listServicesSortedByCategoryAndPrice() {
        for (Service service : hotelService.getServicesSortedByCategoryAndPrice()) {
            System.out.println("Service: " + service.getName() + ", Category: " + service.getCategory() + ", Price: " + service.getPrice());
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



    public void importRooms(String filePath) {
        hotelService.importRoomsFromCSV(filePath);
    }
    
    public void exportRooms(String filePath) {
        hotelService.exportRoomsToCSV(filePath);
    }
    
}
