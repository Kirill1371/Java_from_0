package com.test.ui.handler;

import java.util.Scanner;
import com.test.controller.RoomControllerIMPL;
import com.test.model.Room;

public class GetRoomDetailsHandler implements CommandHandler {

    private final RoomControllerIMPL roomController;

    public GetRoomDetailsHandler(RoomControllerIMPL roomController) {
        this.roomController = roomController;
    }

    @Override
    public void handle(Scanner scanner) {
        System.out.println("Enter room number:");
        int roomNumber = scanner.nextInt();

        roomController.getRoomDetails(roomNumber);

    }
}




// package ui.handler;

// import java.util.Scanner;

// import controller.RoomControllerIMPL;

// public class GetRoomDetailsHandler implements CommandHandler {

//     private final RoomControllerIMPL roomController;

//     public GetRoomDetailsHandler(RoomControllerIMPL roomController) {
//         this.roomController = roomController;
//     }

//     @Override
//     public void handle(Scanner scanner) {
//         System.out.println("Enter room number:");
//         int roomNumber = scanner.nextInt();
//         roomController.getRoomDetails(roomNumber);
//     }
// }
