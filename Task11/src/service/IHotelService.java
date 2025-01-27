package service;

import model.Room;
import model.Service;
import model.Guest;
import model.Stay;
import java.util.Date;
import java.util.List;

import annotations.Component;

public interface IHotelService {
    void checkIn(int roomNumber, Guest guest, Date checkInDate, Date checkOutDate);
    void checkOut(int roomNumber);
    void setRoomStatus(int roomNumber, String status);
    void setRoomPrice(int roomNumber, double price);
    void addRoom(Room room);
    void addRoomToDatabase(int roomNumber, double price, int capacity, int stars);
    void removeRoom(int roomNumber);
    void removeRoomFromDatabase(int roomNumber);
    void addService(String guestname, Service service);
    List<Room> getAllRooms();
    List<Room> getAvailableRooms();
    List<Guest> getAllGuests();
    List<Room> getRoomsSortedByPrice();
    List<Room> getRoomsSortedByCapacity();
    List<Room> getRoomsSortedByStars();
    List<Room> getAvailableRoomsSortedByPrice();
    List<Room> getAvailableRoomsSortedByCapacity();
    List<Room> getAvailableRoomsSortedByStars();
    List<Guest> getGuestsSortedByName();
    List<Guest> getGuestsSortedByCheckOutDate();
    int getTotalAvailableRooms();
    int getTotalGuests();
    List<Room> getRoomsAvailableByDate(Date date);
    double getTotalPaymentForGuest(String guestName);
    List<Stay> getLastThreeStays(int roomNumber);
    List<Service> getGuestServicesSortedByPrice(String guestName);
    List<Service> getGuestServicesSortedByDate(String guestName);
    List<Service> getServicesSortedByCategoryAndPrice();
    Room getRoomDetails(int roomNumber);
    List<Stay> getRoomHistory(int roomNumber);

    void importRoomsFromCSV(String filePath);
    void exportRoomsToCSV(String filePath);
    void importGuestsFromCSV(String filePath);
    void exportGuestsToCSV(String filePath);
}


