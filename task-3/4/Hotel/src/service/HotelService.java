package service;


import model.Room;
import model.Service;
import repository.IHotelRepository;
import java.util.List;

public class HotelService implements IHotelService {
    private IHotelRepository hotelRepository;

    public HotelService(IHotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public void checkIn(int roomNumber) {
        Room room = hotelRepository.getRoom(roomNumber);
        if (room != null) {
            room.setStatus("Occupied");
            System.out.println("Checked in to room: " + roomNumber);
        } else {
            System.out.println("Room not found: " + roomNumber);
        }
    }

    @Override
    public void checkOut(int roomNumber) {
        Room room = hotelRepository.getRoom(roomNumber);
        if (room != null) {
            room.setStatus("Available");
            System.out.println("Checked out from room: " + roomNumber);
        } else {
            System.out.println("Room not found: " + roomNumber);
        }
    }

    @Override
    public void setRoomStatus(int roomNumber, String status) {
        Room room = hotelRepository.getRoom(roomNumber);
        if (room != null) {
            room.setStatus(status);
            System.out.println("Room status updated: " + roomNumber + " to " + status);
        } else {
            System.out.println("Room not found: " + roomNumber);
        }
    }

    @Override
    public void setRoomPrice(int roomNumber, double price) {
        Room room = hotelRepository.getRoom(roomNumber);
        if (room != null) {
            room.setPrice(price);
            System.out.println("Room price updated: " + roomNumber + " to " + price);
        } else {
            System.out.println("Room not found: " + roomNumber);
        }
    }

    @Override
    public void addRoom(Room room) {
        hotelRepository.addRoom(room);
        System.out.println("Room added: " + room.getNumber());
    }

    @Override
    public void removeRoom(int roomNumber) {
        hotelRepository.removeRoom(roomNumber);
        System.out.println("Room removed: " + roomNumber);
    }

    @Override
    public void addService(Service service) {
        hotelRepository.addService(service);
        System.out.println("Service added: " + service.getName());
    }

    @Override
    public List<Room> getAllRooms() {
        return hotelRepository.getAllRooms();
    }

    @Override
    public List<Service> getAllServices() {
        return hotelRepository.getAllServices();
    }
}

