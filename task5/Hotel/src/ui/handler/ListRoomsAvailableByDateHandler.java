package ui.handler;

import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;
import java.text.ParseException;

import controller.HotelController;


public class ListRoomsAvailableByDateHandler implements CommandHandler {
    @Override
    public boolean handle(int choice, Scanner scanner, HotelController hotelController, SimpleDateFormat dateFormat) {
        if (choice == 21) {
            System.out.println("Enter date (yyyy-MM-dd):");
            String dateStr = scanner.nextLine();
            try {
                Date date = dateFormat.parse(dateStr);
                hotelController.listRoomsAvailableByDate(date);
            } catch (ParseException e) {
                System.out.
                println("Invalid date format. Please use yyyy-MM-dd.");
            }
            return true;
        }
        return false;
    }
}
