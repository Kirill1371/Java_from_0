package ru.senla.javacourse.tarasov.hotel.ui.handler;


import java.util.Scanner;
import ru.senla.javacourse.tarasov.hotel.impl.controller.RoomControllerImpl;

public class ListAvailableRoomsHandler implements CommandHandler {
    private final RoomControllerImpl roomController;
    
    public ListAvailableRoomsHandler(RoomControllerImpl roomController) {
        this.roomController = roomController;
    }

    @Override
    public void handle(Scanner scanner) {
        roomController.listAvailableRooms();
    }
}
