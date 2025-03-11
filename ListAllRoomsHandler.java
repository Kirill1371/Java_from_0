package ui.handler;

import java.util.Scanner;

import controller.RoomControllerIMPL;

public class ListAllRoomsHandler implements CommandHandler {
    private final RoomControllerIMPL roomController;
    
    public ListAllRoomsHandler(RoomControllerIMPL roomController) {
        this.roomController = roomController;
    }

    @Override
    public void handle(Scanner scanner) {
        roomController.listAllRooms();
    }
}