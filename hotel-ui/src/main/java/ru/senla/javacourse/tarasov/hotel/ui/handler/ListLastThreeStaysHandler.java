package ru.senla.javacourse.tarasov.hotel.ui.handler;


import java.util.Scanner;
import ru.senla.javacourse.tarasov.hotel.api.controller.RoomController;

public class ListLastThreeStaysHandler implements CommandHandler {

    private final RoomController roomController;

    public ListLastThreeStaysHandler(RoomController roomController) {
        this.roomController = roomController;
    }
    
    @Override
    public void handle(Scanner scanner) {
        System.out.println("Enter room number:");
        int roomNumber = scanner.nextInt();
        //roomController.listLastThreeStays(roomNumber);
    }
}
