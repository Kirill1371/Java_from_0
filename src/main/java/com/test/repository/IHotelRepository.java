package com.test.repository;

import com.test.model.Room;
import com.test.model.Service;
import com.test.model.Stay;
import com.test.model.Guest;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

import com.test.annotations.Component;

public interface IHotelRepository {
    void addRoomToDatabase(int roomNumber, double price, int capacity, int stars);
    Room getRoom(int roomNumber);
    List<Room> getAllRooms();
    List<Room> getAvailableRooms();
    void addService(String guestName, Service service);
    List<Service> getServicesForGuest(String guestName);
    void addStay(Stay stay);
    List<Stay> getAllStays();
    void setStatusAv(int roomNumber);
    Room getRoomById(UUID id);
    void updateRoomStatus(int roomNumber, String newStatus);
    Guest getGuest(String name);
    List<Guest> getAllGuests();

}
