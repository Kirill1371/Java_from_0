package ui.handler;

import java.text.SimpleDateFormat;
import java.util.Scanner;

import controller.HotelController;


public class listRoomsSortedByPriceHandler implements CommandHandler {
    @Override
    public boolean handle(int choice, Scanner scanner, HotelController hotelController, SimpleDateFormat dateFormat) {
        if (choice == 11) {
            hotelController.listRoomsSortedByPrice();
            return true;
        }
        return false;
    }
}
