package ui.handler;

import java.text.SimpleDateFormat;
import java.util.Scanner;

import controller.HotelController;


public class ListGuestsSortedByCheckOutDateHandler implements CommandHandler {
    @Override
    public boolean handle(int choice, Scanner scanner, HotelController hotelController, SimpleDateFormat dateFormat) {
        if (choice == 18) {
            hotelController.listGuestsSortedByCheckOutDate();
            return true;
        }
        return false;
    }
}
