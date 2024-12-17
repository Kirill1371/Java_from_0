package ui.handler;

import java.text.SimpleDateFormat;
import java.util.Scanner;
import controller.HotelController;
import model.Room;

public class AddRoomHandler implements CommandHandler {
    @Override
    public boolean handle(int choice, Scanner scanner, HotelController hotelController, SimpleDateFormat dateFormat) {
        if (choice == 1) {
            System.out.println("Enter room number:");
            int roomNumber = scanner.nextInt();
            System.out.println("Enter room price:");
            double price = scanner.nextDouble();
            System.out.println("Enter room capacity:");
            int capacity = scanner.nextInt();
            System.out.println("Enter room stars:");
            int stars = scanner.nextInt();
            //hotelController.addRoom(new Room(0, roomNumber, price, capacity, stars));
            hotelController.addRoom(new Room(0, roomNumber, "Available", price, capacity, stars));
            return true;
        }
        return false;
    }
}
