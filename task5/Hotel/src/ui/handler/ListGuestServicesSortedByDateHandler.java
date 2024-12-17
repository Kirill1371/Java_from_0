package ui.handler;

import java.text.SimpleDateFormat;
import java.util.Scanner;

import controller.HotelController;


public class ListGuestServicesSortedByDateHandler implements CommandHandler {
    @Override
    public boolean handle(int choice, Scanner scanner, HotelController hotelController, SimpleDateFormat dateFormat) {
        if (choice == 25) {
            System.out.println("Enter guest name:");
            String guestName = scanner.nextLine();
            hotelController.listGuestServicesSortedByDate(guestName);
            return true;
        }
        return false;
    }
}
