package ru.senla.javacourse.tarasov.hotel.ui.handler;


import java.util.Scanner;
import ru.senla.javacourse.tarasov.hotel.impl.controller.RoomControllerImpl;

public class ListAvailableRoomsSortedByStarsHandler implements CommandHandler {

    private final RoomControllerImpl roomController;

    public ListAvailableRoomsSortedByStarsHandler(RoomControllerImpl roomController) {
        this.roomController = roomController;
    }
    @Override
    public void handle(Scanner scanner) {
        roomController.listAvailableRoomsSortedByStars();
    }
}
