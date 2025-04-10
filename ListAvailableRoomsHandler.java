package ui.handler;

import java.util.Scanner;

import controller.RoomControllerIMPL;

public class ListAvailableRoomsHandler implements CommandHandler {
    private final RoomControllerIMPL roomController;
    
    public ListAvailableRoomsHandler(RoomControllerIMPL roomController) {
        this.roomController = roomController;
    }

    @Override
    public void handle(Scanner scanner) {
        roomController.listAvailableRooms();
    }
}
