package ui.handler;

import java.text.SimpleDateFormat;
import java.util.Scanner;

import controller.HotelController;


public class ListAvailableRoomsSortedByPriceHandler implements CommandHandler {
    @Override
    public boolean handle(int choice, Scanner scanner, HotelController hotelController, SimpleDateFormat dateFormat) {
        if (choice == 14) {
            hotelController.listAvailableRoomsSortedByPrice();
            return true;
        }
        return false;
    }
}
