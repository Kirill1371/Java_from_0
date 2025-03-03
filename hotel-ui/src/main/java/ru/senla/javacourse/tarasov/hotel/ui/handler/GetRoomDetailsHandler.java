package ru.senla.javacourse.tarasov.hotel.ui.handler;


import java.util.Scanner;
import ru.senla.javacourse.tarasov.hotel.api.controller.RoomController;

public class GetRoomDetailsHandler implements CommandHandler {

    private final RoomController roomController;

    public GetRoomDetailsHandler(RoomController roomController) {
        this.roomController = roomController;
    }

    @Override
    public void handle(Scanner scanner) {
        System.out.println("Enter room number:");
        int roomNumber = scanner.nextInt();

        roomController.getRoomDetails(roomNumber);

    }
}



