package ui.handler;

import java.util.Scanner;

import controller.ServiceControllerIMPL;

public class ListGuestServicesSortedByPriceHandler implements CommandHandler {

    private final ServiceControllerIMPL serviceController;

    public ListGuestServicesSortedByPriceHandler(ServiceControllerIMPL serviceController) {
        this.serviceController = serviceController;
    }

    @Override
    public void handle(Scanner scanner) {
        System.out.println("Enter guest name:");
        String guestName = scanner.nextLine();
        serviceController.listGuestServicesSortedByPrice(guestName);
    }
}