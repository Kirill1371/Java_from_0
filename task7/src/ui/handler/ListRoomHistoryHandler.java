package ui.handler;

import controller.RoomControllerIMPL;

import java.util.Scanner;

public class ListRoomHistoryHandler implements CommandHandler {
    private final RoomControllerIMPL roomController;

    public ListRoomHistoryHandler(RoomControllerIMPL roomController) {
        this.roomController = roomController;
    }

    @Override
    public void handle(Scanner scanner) {
        System.out.println("Enter room number to view history:");
        int roomNumber = scanner.nextInt();
        scanner.nextLine(); // consume newline

        roomController.listRoomHistory(roomNumber);
    }
}
