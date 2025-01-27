package repository;

import model.Room;
import model.Service;
import model.Stay;
import model.Guest;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

import annotations.Component;

public interface IHotelRepository {
    void addRoom(Room room);
    void addRoomToDatabase(int roomNumber, double price, int capacity, int stars);
    void removeRoom(int roomNumber);
    Room getRoom(int roomNumber);
    List<Room> getAllRooms();
    List<Room> getAvailableRooms();
    void addService(String guestName, Service service);
    List<Service> getServicesForGuest(String guestName);
    List<Service> getGuestServicesSortedByPrice(String guestName); 
    List<Service> getAllServices();
    void addStay(Stay stay);
    //void addService(int roomNumber, String guestName, Date checkInDate, Date checkOutDate);
    List<Stay> getAllStays();
    //Guest getGuestById(UUID id);
    void setStatusAv(int roomNumber);
    Room getRoomById(UUID id);
    void updateRoomStatus(int roomNumber, String newStatus);
    void addGuest(Guest guest);
    Guest getGuest(String name);
    List<Guest> getAllGuests();

    List<Stay> getRoomHistory(int roomNumber);

}
