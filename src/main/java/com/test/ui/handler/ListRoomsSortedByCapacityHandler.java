package com.test.ui.handler;

import java.util.Scanner;

import com.test.controller.RoomControllerIMPL;

public class ListRoomsSortedByCapacityHandler implements CommandHandler {

    private final RoomControllerIMPL roomController;

    public ListRoomsSortedByCapacityHandler(RoomControllerIMPL roomController) {
        this.roomController = roomController;
    }
    
    @Override
    public void handle(Scanner scanner) {
        roomController.listRoomsSortedByCapacity();
    }
}
