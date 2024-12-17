package ui.handler;

import java.text.SimpleDateFormat;
import java.util.Scanner;
import controller.HotelController;

public class ExitHandler implements CommandHandler {
    @Override
    public boolean handle(int choice, Scanner scanner, HotelController hotelController, SimpleDateFormat dateFormat) {
        if (choice == 28) {
            System.out.println("Exiting...");
            scanner.close();
            System.exit(0);
            return true;
        }
        return false;
    }
}
