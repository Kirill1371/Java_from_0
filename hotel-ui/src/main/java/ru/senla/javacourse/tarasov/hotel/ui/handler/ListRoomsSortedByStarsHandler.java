package ru.senla.javacourse.tarasov.hotel.ui.handler;


import java.util.Scanner;
import ru.senla.javacourse.tarasov.hotel.impl.controller.RoomControllerImpl;

public class ListRoomsSortedByStarsHandler implements CommandHandler {

    private final RoomControllerImpl roomController;

    public ListRoomsSortedByStarsHandler(RoomControllerImpl roomController) {
        this.roomController = roomController;
    }
    @Override
    public void handle(Scanner scanner) {
        roomController.listRoomsSortedByStars();
    }
}
