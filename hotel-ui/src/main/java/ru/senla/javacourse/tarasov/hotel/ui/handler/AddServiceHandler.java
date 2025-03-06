package ru.senla.javacourse.tarasov.hotel.ui.handler;

import java.util.Scanner;
import ru.senla.javacourse.tarasov.hotel.api.controller.ServiceController;
import ru.senla.javacourse.tarasov.hotel.api.dto.ServiceDto;

public class AddServiceHandler implements CommandHandler {
    private final ServiceController serviceController;

    public AddServiceHandler(ServiceController serviceController) {
        this.serviceController = serviceController;
    }

    @Override
    public void handle(Scanner scanner) {
        System.out.println("Enter guest name:");
        String guestName = scanner.nextLine();

        System.out.println("Enter service name:");
        String serviceName = scanner.nextLine();

        System.out.println("Enter service price:");
        double price = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Enter service category:");
        String category = scanner.nextLine();

        // todo поправить

        ServiceDto serviceDto = new ServiceDto(serviceName, price, category);
        serviceController.addService(guestName, serviceDto);

        System.out.println("Service added successfully for guest: " + guestName);
    }
}


