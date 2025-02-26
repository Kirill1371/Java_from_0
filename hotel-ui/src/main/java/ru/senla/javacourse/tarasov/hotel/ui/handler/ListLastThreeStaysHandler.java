package ru.senla.javacourse.tarasov.hotel.ui.handler;


import java.util.Scanner;
import ru.senla.javacourse.tarasov.hotel.impl.controller.RoomControllerImpl;

public class ListLastThreeStaysHandler implements CommandHandler {

    private final RoomControllerImpl roomController;

    public ListLastThreeStaysHandler(RoomControllerImpl roomController) {
        this.roomController = roomController;
    }
    
    @Override
    public void handle(Scanner scanner) {
        System.out.println("Enter room number:");
        int roomNumber = scanner.nextInt();
        roomController.listLastThreeStays(roomNumber);
    }
}
