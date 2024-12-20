package model;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private List<Room> rooms;
    private List<Service> services;

    public Hotel() {
        rooms = new ArrayList<>();
        services = new ArrayList<>();
    }

    public void addRoom(Room room) {
        rooms.add(room);
        System.out.println("Room added: " + room.getNumber());
    }

    public void removeRoom(int roomNumber) {
        rooms.removeIf(room -> room.getNumber() == roomNumber);
        System.out.println("Room removed: " + roomNumber);
    }

    public void checkIn(int roomNumber) {
        for (Room room : rooms) {
            if (room.getNumber() == roomNumber) {
                room.setStatus("Occupied");
                System.out.println("Checked in to room: " + roomNumber);
                return;
            }
        }
        System.out.println("Room not found: " + roomNumber);
    }

    public void checkOut(int roomNumber) {
        for (Room room : rooms) {
            if (room.getNumber() == roomNumber) {
                room.setStatus("Available");
                System.out.println("Checked out from room: " + roomNumber);
                return;
            }
        }
        System.out.println("Room not found: " + roomNumber);
    }

    public void setRoomStatus(int roomNumber, String status) {
        for (Room room : rooms) {
            if (room.getNumber() == roomNumber) {
                room.setStatus(status);
                System.out.println("Room status updated: " + roomNumber + " to " + status);
                return;
            }
        }
        System.out.println("Room not found: " + roomNumber);
    }

    public void setRoomPrice(int roomNumber, double price) {
        for (Room room : rooms) {
            if (room.getNumber() == roomNumber) {
                room.setPrice(price);
                System.out.println("Room price updated: " + roomNumber + " to " + price);
                return;
            }
        }
        System.out.println("Room not found: " + roomNumber);
    }

    public void addService(Service service) {
        services.add(service);
        System.out.println("Service added: " + service.getName());
    }
}
