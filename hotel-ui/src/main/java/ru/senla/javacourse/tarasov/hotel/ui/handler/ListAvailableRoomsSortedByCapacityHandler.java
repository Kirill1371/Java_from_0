package ru.senla.javacourse.tarasov.hotel.ui.handler;


import java.util.Scanner;
import ru.senla.javacourse.tarasov.hotel.api.controller.RoomController;

public class ListAvailableRoomsSortedByCapacityHandler implements
    CommandHandler {

    private final RoomController roomController;

    public ListAvailableRoomsSortedByCapacityHandler(RoomController roomController) {
        this.roomController = roomController;
    }
    @Override
    public void handle(Scanner scanner) {
        roomController.listAvailableRoomsSortedByCapacity();
    }
}
