package ui.handler;

import java.util.Scanner;

import controller.RoomControllerIMPL;

public class ListRoomsSortedByPriceHandler implements CommandHandler {

    private final RoomControllerIMPL roomController;

    public ListRoomsSortedByPriceHandler(RoomControllerIMPL roomController) {
        this.roomController = roomController;
    }

    @Override
    public void handle(Scanner scanner) {
        roomController.listRoomsSortedByPrice();
    }
}
