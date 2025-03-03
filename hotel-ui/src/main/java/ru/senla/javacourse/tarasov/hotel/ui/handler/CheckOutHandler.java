package ru.senla.javacourse.tarasov.hotel.ui.handler;


import java.util.Scanner;
import ru.senla.javacourse.tarasov.hotel.api.controller.CheckController;

public class CheckOutHandler implements CommandHandler {

    private final CheckController checkController;

    public CheckOutHandler(CheckController checkController) {
        this.checkController = checkController;
    }

    @Override
    public void handle(Scanner scanner) {
        System.out.println("Enter room number:");
        int roomNumber = scanner.nextInt();
        scanner.nextLine(); 

        checkController.checkOut(roomNumber);
        System.out.println("Guest checked out successfully.");
    }
}
