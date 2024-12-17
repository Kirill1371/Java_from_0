package ui.handler;

import java.text.SimpleDateFormat;
import java.util.Scanner;

import controller.HotelController;


public class ListAvailableRoomsSortedByCapacityHandler implements CommandHandler {
    @Override
    public boolean handle(int choice, Scanner scanner, HotelController hotelController, SimpleDateFormat dateFormat) {
        if (choice == 15) {
            hotelController.listAvailableRoomsSortedByCapacity();
            return true;
        }
        return false;
    }
}
