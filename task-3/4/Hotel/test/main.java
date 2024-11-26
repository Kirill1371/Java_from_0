package test;

import model.Hotel;
import model.Room;
import model.Service;

public class main {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();

        hotel.addRoom(new Room(101, 100.0));
        hotel.addRoom(new Room(102, 150.0));

        hotel.addService(new Service("WiFi", 10.0));
        hotel.addService(new Service("Breakfast", 20.0));

        hotel.checkIn(101);

        hotel.setRoomStatus(102, "Under Maintenance");

        hotel.setRoomPrice(101, 120.0);

        hotel.checkOut(101);

        hotel.removeRoom(102);
    }
}
