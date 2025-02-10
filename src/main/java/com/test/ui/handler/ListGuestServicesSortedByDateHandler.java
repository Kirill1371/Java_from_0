package com.test.ui.handler;

import java.util.Scanner;

import com.test.controller.ServiceControllerIMPL;

public class ListGuestServicesSortedByDateHandler implements CommandHandler {

    private final ServiceControllerIMPL serviceController;

    public ListGuestServicesSortedByDateHandler(ServiceControllerIMPL serviceController) {
        this.serviceController = serviceController;
    }

    @Override
    public void handle(Scanner scanner) {
        System.out.println("Enter guest name:");
        String guestName = scanner.nextLine();
        serviceController.listGuestServicesSortedByDate(guestName);
    }
}
