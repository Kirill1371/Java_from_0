package ui.handler;

import java.text.SimpleDateFormat;
import java.util.Scanner;

import controller.HotelController;
import model.Service;

public class AddServiceHandler implements CommandHandler {
    @Override
    public boolean handle(int choice, Scanner scanner, HotelController hotelController, SimpleDateFormat dateFormat) {
        if (choice == 7) {
            System.out.println("Enter service name:");
            String serviceName = scanner.nextLine();
            System.out.println("Enter service price:");
            double price = scanner.nextDouble();
            scanner.nextLine(); // consume newline
            System.out.println("Enter service category:");
            String category = scanner.nextLine();
            hotelController.addService(new Service(serviceName, price, category));
            return true;
        }
        return false;
    }
}
