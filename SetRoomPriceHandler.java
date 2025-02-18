package ui.handler;

import java.util.Scanner;

import controller.RoomControllerIMPL;

public class SetRoomPriceHandler implements CommandHandler {

    private final RoomControllerIMPL roomController;

    public SetRoomPriceHandler(RoomControllerIMPL roomController) {
        this.roomController = roomController;
    }
    @Override
    public void handle(Scanner scanner) {
        System.out.println("Enter room number:");
        int roomNumber = scanner.nextInt();
        System.out.println("Enter room price:");
        double price = scanner.nextDouble();
        roomController.setRoomPrice(roomNumber, price);
    }
}
