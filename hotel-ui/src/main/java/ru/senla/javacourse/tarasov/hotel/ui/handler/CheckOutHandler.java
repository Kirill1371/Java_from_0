package ru.senla.javacourse.tarasov.hotel.ui.handler;


import java.util.Scanner;
import ru.senla.javacourse.tarasov.hotel.impl.controller.CheckControllerImpl;

public class CheckOutHandler implements CommandHandler {

    private final CheckControllerImpl checkController;

    public CheckOutHandler(CheckControllerImpl checkController) {
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
