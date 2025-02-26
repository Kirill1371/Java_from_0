package ru.senla.javacourse.tarasov.hotel.ui.handler;


import java.util.Scanner;
import ru.senla.javacourse.tarasov.hotel.impl.controller.ServiceControllerImpl;

public class ListGuestServicesSortedByDateHandler implements CommandHandler {

    private final ServiceControllerImpl serviceController;

    public ListGuestServicesSortedByDateHandler(ServiceControllerImpl serviceController) {
        this.serviceController = serviceController;
    }

    @Override
    public void handle(Scanner scanner) {
        System.out.println("Enter guest name:");
        String guestName = scanner.nextLine();
        serviceController.listGuestServicesSortedByDate(guestName);
    }
}
