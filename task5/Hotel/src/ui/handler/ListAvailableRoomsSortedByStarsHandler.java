package ui.handler;

import java.text.SimpleDateFormat;
import java.util.Scanner;

import controller.HotelController;


public class ListAvailableRoomsSortedByStarsHandler implements CommandHandler {
    @Override
    public boolean handle(int choice, Scanner scanner, HotelController hotelController, SimpleDateFormat dateFormat) {
        if (choice == 16) {
            hotelController.listAvailableRoomsSortedByStars();
            return true;
        }
        return false;
    }
}
