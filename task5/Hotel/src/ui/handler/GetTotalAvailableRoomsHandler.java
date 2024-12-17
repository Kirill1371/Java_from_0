package ui.handler;

import java.text.SimpleDateFormat;
import java.util.Scanner;

import controller.HotelController;


public class GetTotalAvailableRoomsHandler implements CommandHandler {
    @Override
    public boolean handle(int choice, Scanner scanner, HotelController hotelController, SimpleDateFormat dateFormat) {
        if (choice == 19) {
            hotelController.getTotalAvailableRooms();
            return true;
        }
        return false;
    }
}
