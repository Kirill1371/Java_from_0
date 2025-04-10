package repository;

import model.Room;
import model.Service;
import model.Guest;
import java.util.List;

public interface IHotelRepository {
    void addRoom(Room room);
    void removeRoom(int roomNumber);
    Room getRoom(int roomNumber);
    List<Room> getAllRooms();
    void addService(Service service);
    List<Service> getAllServices();
    void addGuest(Guest guest);
    Guest getGuest(String name);
    List<Guest> getAllGuests();
}
