package ui.handler;

import java.text.SimpleDateFormat;
import java.util.Scanner;

import controller.HotelController;


public class ListAvailableRoomsHandler implements CommandHandler {
    @Override
    public boolean handle(int choice, Scanner scanner, HotelController hotelController, SimpleDateFormat dateFormat) {
        if (choice == 9) {
            hotelController.listAvailableRooms();
            return true;
        }
        return false;
    }
}
