package ui.handler;

import java.text.SimpleDateFormat;
import java.util.Scanner;

import controller.HotelController;


public class GetTotalPaymentForGuestHandler implements CommandHandler {
    @Override
    public boolean handle(int choice, Scanner scanner, HotelController hotelController, SimpleDateFormat dateFormat) {
        if (choice == 22) {
            System.out.println("Enter guest name:");
            String guestName = scanner.nextLine();
            hotelController.getTotalPaymentForGuest(guestName);
            return true;
        }
        return false;
    }
}
