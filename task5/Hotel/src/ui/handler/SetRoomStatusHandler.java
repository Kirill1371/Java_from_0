package ui.handler;

import java.text.SimpleDateFormat;
import java.util.Scanner;

import controller.HotelController;

public class SetRoomStatusHandler implements CommandHandler {
    @Override
    public boolean handle(int choice, Scanner scanner, HotelController hotelController, SimpleDateFormat dateFormat) {
        if (choice == 5) {
            System.out.println("Enter room number:");
            int roomNumber = scanner.nextInt();
            scanner.nextLine(); // consume newline
            System.out.println("Enter room status:");
            String status = scanner.nextLine();
            hotelController.setRoomStatus(roomNumber, status);
            return true;
        }
        return false;
    }
}
