package ui.handler;

import java.util.Scanner;

import controller.RoomControllerIMPL;

public class ListLastThreeStaysHandler implements CommandHandler {

    private final RoomControllerIMPL roomController;

    public ListLastThreeStaysHandler(RoomControllerIMPL roomController) {
        this.roomController = roomController;
    }
    
    @Override
    public void handle(Scanner scanner) {
        System.out.println("Enter room number:");
        int roomNumber = scanner.nextInt();
        roomController.listLastThreeStays(roomNumber);
    }
}
