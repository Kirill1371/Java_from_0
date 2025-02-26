package ru.senla.javacourse.tarasov.hotel.impl.repository;





import java.util.List;
import java.util.UUID;
import ru.senla.javacourse.tarasov.hotel.db.entity.Guest;
import ru.senla.javacourse.tarasov.hotel.db.entity.Room;
import ru.senla.javacourse.tarasov.hotel.db.entity.Stay;

public interface HotelRepository {
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
