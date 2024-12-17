package ui.handler;

import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;
import java.text.ParseException;
import controller.HotelController;
import model.Guest;

public class CheckInHandler implements CommandHandler {
    @Override
    public boolean handle(int choice, Scanner scanner, HotelController hotelController, SimpleDateFormat dateFormat) {
        if (choice == 3) {
            System.out.println("Enter room number:");
            int roomNumber = scanner.nextInt();
            scanner.nextLine(); // consume newline
            System.out.println("Enter guest name:");
            String guestName = scanner.nextLine();
            System.out.println("Enter check-in date (yyyy-MM-dd):");
            String checkInDateStr = scanner.nextLine();
            System.out.
            println("Enter check-out date (yyyy-MM-dd):");
            String checkOutDateStr = scanner.nextLine();
            try {
                Date checkInDate = dateFormat.parse(checkInDateStr);
                Date checkOutDate = dateFormat.parse(checkOutDateStr);
                Guest guest = new Guest(guestName);
                hotelController.checkIn(roomNumber, guest, checkInDate, checkOutDate);
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please use yyyy-MM-dd.");
            }
            return true;
        }
        return false;
    }
}
