package ui.handler;

import java.text.SimpleDateFormat;
import java.util.Scanner;

import controller.HotelController;


public class ListRoomsSortedByCapacityHandler implements CommandHandler {
    @Override
    public boolean handle(int choice, Scanner scanner, HotelController hotelController, SimpleDateFormat dateFormat) {
        if (choice == 12) {
            hotelController.listRoomsSortedByCapacity();
            return true;
        }
        return false;
    }
}
