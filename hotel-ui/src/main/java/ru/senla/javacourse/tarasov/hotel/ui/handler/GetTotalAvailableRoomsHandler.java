package ru.senla.javacourse.tarasov.hotel.ui.handler;


import java.util.Scanner;
import ru.senla.javacourse.tarasov.hotel.impl.controller.RoomControllerImpl;

public class GetTotalAvailableRoomsHandler implements CommandHandler {

    private final RoomControllerImpl roomController;

    public GetTotalAvailableRoomsHandler(RoomControllerImpl roomController) {
        this.roomController = roomController;
    }

    @Override
    public void handle(Scanner scanner) {
        // Получаем количество доступных комнат через контроллер
        roomController.getTotalAvailableRooms();
    }
}



// package ui.handler;

// import java.util.Scanner;

// import controller.RoomControllerIMPL;

// public class GetTotalAvailableRoomsHandler implements CommandHandler {

//     private final RoomControllerIMPL roomController;

//     public GetTotalAvailableRoomsHandler(RoomControllerIMPL roomController) {
//         this.roomController =  roomController;
//     }

//     @Override
//     public void handle(Scanner scanner) {
//         roomController.getTotalAvailableRooms();
//     }
// }
