package ui.handler;

import java.util.Scanner;

import controller.RoomControllerIMPL;

public class listRoomsSortedByPriceHandler implements CommandHandler {

    private final RoomControllerIMPL roomController;

    public listRoomsSortedByPriceHandler(RoomControllerIMPL roomController) {
        this.roomController = roomController;
    }

    @Override
    public void handle(Scanner scanner) {
        roomController.listRoomsSortedByPrice();
    }
}
