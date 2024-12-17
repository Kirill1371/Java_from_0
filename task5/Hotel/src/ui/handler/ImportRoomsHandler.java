package ui.handler;

import java.text.SimpleDateFormat;
import java.util.Scanner;

import controller.HotelController;


public class ImportRoomsHandler implements CommandHandler {
    @Override
    public boolean handle(int choice, Scanner scanner, HotelController hotelController, SimpleDateFormat dateFormat) {
        if (choice == 29) {
            System.out.println("Enter file path to import rooms:");
            String filePath = scanner.nextLine();
            hotelController.importRooms(filePath);
            return true;
        }
        return false;
    }
    public String getCommandName() {
        return "Import Rooms";
    }
}
