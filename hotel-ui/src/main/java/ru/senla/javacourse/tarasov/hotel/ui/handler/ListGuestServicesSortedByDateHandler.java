package ru.senla.javacourse.tarasov.hotel.ui.handler;


import java.util.Scanner;
import ru.senla.javacourse.tarasov.hotel.api.controller.ServiceController;

public class ListGuestServicesSortedByDateHandler implements CommandHandler {

    private final ServiceController serviceController;

    public ListGuestServicesSortedByDateHandler(ServiceController serviceController) {
        this.serviceController = serviceController;
    }

    @Override
    public void handle(Scanner scanner) {
        System.out.println("Enter guest name:");
        String guestName = scanner.nextLine();
        serviceController.listGuestServicesSortedByDate(guestName);
    }
}
