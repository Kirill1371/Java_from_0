package ui.handler;

import java.util.Scanner;

import controller.ServiceControllerIMPL;

public class ListServicesSortedByCategoryAndPriceHandler implements CommandHandler {
    private final ServiceControllerIMPL serviceController;
    
    public ListServicesSortedByCategoryAndPriceHandler(ServiceControllerIMPL serviceController) {
        this.serviceController = serviceController;
    }

    @Override
    public void handle(Scanner scanner) {
        serviceController.listServicesSortedByCategoryAndPrice();
    }
}