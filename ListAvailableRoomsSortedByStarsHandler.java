package ui.handler;

import java.util.Scanner;

import controller.RoomControllerIMPL;

public class ListAvailableRoomsSortedByStarsHandler implements CommandHandler {

    private final RoomControllerIMPL roomController;

    public ListAvailableRoomsSortedByStarsHandler(RoomControllerIMPL roomController) { 
        this.roomController = roomController;
    }
    @Override
    public void handle(Scanner scanner) {
        roomController.listAvailableRoomsSortedByStars();
    }
}
