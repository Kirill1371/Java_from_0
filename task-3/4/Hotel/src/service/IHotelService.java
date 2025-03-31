package service;

import model.Room;
import model.Service;
import java.util.List;

public interface IHotelService {
    void checkIn(int roomNumber);
    void checkOut(int roomNumber);
    void setRoomStatus(int roomNumber, String status);
    void setRoomPrice(int roomNumber, double price);
    void addRoom(Room room);
    void removeRoom(int roomNumber);
    void addService(Service service);
    List<Room> getAllRooms();
    List<Service> getAllServices();
}

