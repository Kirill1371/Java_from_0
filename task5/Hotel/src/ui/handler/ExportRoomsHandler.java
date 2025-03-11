package ui.handler;

import java.text.SimpleDateFormat;
import java.util.Scanner;

import controller.HotelControllerImpl;

public class ExportRoomsHandler implements CommandHandler {
    @Override
    public boolean handle(int choice, Scanner scanner, HotelControllerImpl hotelController, SimpleDateFormat dateFormat) {
        if (choice == 30) {
            System.out.println("Enter file path to export rooms:");
            String filePath = scanner.nextLine();
            hotelController.exportRooms(filePath);
            return true;
        }
        return false;
    }
    public String getCommandName() {
        return "Import Rooms";
    }
}
