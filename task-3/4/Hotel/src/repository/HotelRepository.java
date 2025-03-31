package repository;


import model.Room;
import model.Service;
import java.util.ArrayList;
import java.util.List;

public class HotelRepository implements IHotelRepository {
    private List<Room> rooms;
    private List<Service> services;

    public HotelRepository() {
        rooms = new ArrayList<>();
        services = new ArrayList<>();
    }

    @Override
    public void addRoom(Room room) {
        rooms.add(room);
    }

    @Override
    public void removeRoom(int roomNumber) {
        rooms.removeIf(room -> room.getNumber() == roomNumber);
    }

    @Override
    public Room getRoom(int roomNumber) {
        return rooms.stream().filter(room -> room.getNumber() == roomNumber).findFirst().orElse(null);
    }

    @Override
    public List<Room> getAllRooms() {
        return rooms;
    }

    @Override
    public void addService(Service service) {
        services.add(service);
    }

    @Override
    public List<Service> getAllServices() {
        return services;
    }
}
