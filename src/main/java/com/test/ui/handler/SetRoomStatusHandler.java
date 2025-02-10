package com.test.ui.handler;

import java.util.Scanner;

import com.test.controller.RoomControllerIMPL;

public class SetRoomStatusHandler implements CommandHandler {
    

    private final RoomControllerIMPL roomController;

    public SetRoomStatusHandler(RoomControllerIMPL roomController) {
        this.roomController = roomController;
    }
    @Override
    public void handle(Scanner scanner) {
        System.out.println("Enter room number:");
        int roomNumber = scanner.nextInt();
        scanner.nextLine(); 
        System.out.println("Enter room status:");
        String status = scanner.nextLine();
        roomController.setRoomStatus(roomNumber, status);

    }
}
