package ru.senla.javacourse.tarasov.hotel.ui.handler;


import java.util.Scanner;
import ru.senla.javacourse.tarasov.hotel.api.controller.RoomController;

public class ListRoomsSortedByCapacityHandler implements CommandHandler {

    private final RoomController roomController;

    public ListRoomsSortedByCapacityHandler(RoomController roomController) {
        this.roomController = roomController;
    }
    
    @Override
    public void handle(Scanner scanner) {
        roomController.listRoomsSortedByCapacity();
    }
}
