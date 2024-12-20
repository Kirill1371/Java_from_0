package ui.handler;

import java.util.Scanner;

import controller.RoomControllerIMPL;

public class ListRoomsSortedByStarsHandler implements CommandHandler {

    private final RoomControllerIMPL roomController;

    public ListRoomsSortedByStarsHandler(RoomControllerIMPL roomController) {
        this.roomController = roomController;
    }
    @Override
    public void handle(Scanner scanner) {
        roomController.listRoomsSortedByStars();
    }
}
