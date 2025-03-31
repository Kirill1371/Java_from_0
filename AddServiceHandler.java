package ui.handler;

import java.util.Scanner;

import controller.ServiceControllerIMPL;
import model.Service;

public class AddServiceHandler implements CommandHandler {
    private final ServiceControllerIMPL serviceController;

    public AddServiceHandler(ServiceControllerIMPL serviceController) {
        this.serviceController = serviceController;
    }

    @Override
    public void handle(Scanner scanner) {
        System.out.println("Enter service name:");
        String serviceName = scanner.nextLine();
        System.out.println("Enter service price:");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Enter service category:");
        String category = scanner.nextLine();

        Service newService = new Service(serviceName, price, category);
        serviceController.addService(newService);
        System.out.println("Room added successfully.");
    }
}
