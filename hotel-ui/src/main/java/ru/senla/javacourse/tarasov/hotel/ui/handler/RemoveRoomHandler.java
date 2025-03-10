package ru.senla.javacourse.tarasov.hotel.ui.handler;


import java.util.Scanner;
import ru.senla.javacourse.tarasov.hotel.api.controller.RoomController;

public class RemoveRoomHandler implements CommandHandler {
    private final RoomController roomController;

    public RemoveRoomHandler(RoomController roomController) {
        this.roomController = roomController;
    }

    @Override
    public void handle(Scanner scanner) {
        System.out.println("Enter room number:");
        int roomNumber = scanner.nextInt();

        try {
            roomController.removeRoom(roomNumber);
            System.out.println("Room removed successfully.");
        } catch (Exception e) {
            System.out.println("Failed to remove the room: " + e.getMessage());
        }
    }
}


// package ui.handler;

// import java.util.Scanner;

// import controller.RoomControllerIMPL;

// public class RemoveRoomHandler implements CommandHandler {
//     private final RoomControllerIMPL roomController;

//     public RemoveRoomHandler(RoomControllerIMPL roomController) {
//         this.roomController = roomController;
//     }

//     @Override
//     public void handle(Scanner scanner) {
//         System.out.println("Enter room number:");
//         int roomNumber = scanner.nextInt();
//         roomController.removeRoom(roomNumber);
//         System.out.println("Room removed successfully.");
//     }
// }





