package test;

import controller.HotelController;
import model.Room;
import model.Service;
import repository.HotelRepository;
import service.HotelService;

public class HotelManagementTest {
    public static void main(String[] args) {
        HotelRepository hotelRepository = new HotelRepository();
        HotelService hotelService = new HotelService(hotelRepository);
        HotelController hotelController = new HotelController(hotelService);

        hotelController.addRoom(new Room(101, 100.0));
        hotelController.addRoom(new Room(102, 150.0));

        hotelController.addService(new Service("WiFi", 10.0));
        hotelController.addService(new Service("Breakfast", 20.0));

        hotelController.checkIn(101);

        hotelController.setRoomStatus(102, "Under Maintenance");

        hotelController.setRoomPrice(101, 120.0);

        hotelController.checkOut(101);

        hotelController.removeRoom(102);

        hotelController.listAllRooms();

        hotelController.listAllServices();
    }
}
