package ui.handler;

import java.text.SimpleDateFormat;
import java.util.Scanner;

import controller.HotelController;


public class ListLastThreeStaysHandler implements CommandHandler {
    @Override
    public boolean handle(int choice, Scanner scanner, HotelController hotelController, SimpleDateFormat dateFormat) {
        if (choice == 23) {
            System.out.println("Enter room number:");
            int roomNumber = scanner.nextInt();
            hotelController.listLastThreeStays(roomNumber);
            return true;
        }
        return false;
    }
}
