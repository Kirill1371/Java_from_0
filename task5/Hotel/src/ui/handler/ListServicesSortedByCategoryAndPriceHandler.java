package ui.handler;

import java.text.SimpleDateFormat;
import java.util.Scanner;

import controller.HotelController;


public class ListServicesSortedByCategoryAndPriceHandler implements CommandHandler {
    @Override
    public boolean handle(int choice, Scanner scanner, HotelController hotelController, SimpleDateFormat dateFormat) {
        if (choice == 26) {
            hotelController.listServicesSortedByCategoryAndPrice();
            return true;
        }
        return false;
    }
}
