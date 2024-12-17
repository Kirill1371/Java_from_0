package ui.handler;

import java.text.SimpleDateFormat;
import java.util.Scanner;

import controller.HotelController;


public class GetRoomDetailsHandler implements CommandHandler {
    @Override
    public boolean handle(int choice, Scanner scanner, HotelController hotelController, SimpleDateFormat dateFormat) {
        if (choice == 27) {
            System.out.println("Enter room number:");
            int roomNumber = scanner.nextInt();
            hotelController.getRoomDetails(roomNumber);
            return true;
        }
        return false;
    }
}
