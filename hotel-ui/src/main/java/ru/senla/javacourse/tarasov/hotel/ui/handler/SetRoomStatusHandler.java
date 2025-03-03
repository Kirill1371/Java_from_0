package ru.senla.javacourse.tarasov.hotel.ui.handler;


import java.util.Scanner;
import ru.senla.javacourse.tarasov.hotel.api.controller.RoomController;

public class SetRoomStatusHandler implements CommandHandler {
    

    private final RoomController roomController;

    public SetRoomStatusHandler(RoomController roomController) {
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
