package ui.handler;

import java.text.SimpleDateFormat;
import java.util.Scanner;

import controller.HotelController;

public class SetRoomPriceHandler implements CommandHandler {
    @Override
    public boolean handle(int choice, Scanner scanner, HotelController hotelController, SimpleDateFormat dateFormat) {
        if (choice == 6) {
            System.out.println("Enter room number:");
            int roomNumber = scanner.nextInt();
            System.out.println("Enter room price:");
            double price = scanner.nextDouble();
            hotelController.setRoomPrice(roomNumber, price);
            return true;
        }
        return false;
    }
}
