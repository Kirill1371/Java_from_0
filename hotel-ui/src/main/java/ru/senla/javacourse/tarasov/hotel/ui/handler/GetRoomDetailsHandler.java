package ru.senla.javacourse.tarasov.hotel.ui.handler;


import java.util.Scanner;
import ru.senla.javacourse.tarasov.hotel.impl.controller.RoomControllerImpl;

public class GetRoomDetailsHandler implements CommandHandler {

    private final RoomControllerImpl roomController;

    public GetRoomDetailsHandler(RoomControllerImpl roomController) {
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
