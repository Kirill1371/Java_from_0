package ui.handler;

import java.util.Scanner;

import controller.CheckControllerIMPL;

public class CheckOutHandler implements CommandHandler {

    private final CheckControllerIMPL checkController;

    public CheckOutHandler(CheckControllerIMPL checkController) {
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
