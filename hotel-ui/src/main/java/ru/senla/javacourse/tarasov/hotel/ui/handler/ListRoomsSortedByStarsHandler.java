package ru.senla.javacourse.tarasov.hotel.ui.handler;


import java.util.Scanner;
import ru.senla.javacourse.tarasov.hotel.api.controller.RoomController;

public class ListRoomsSortedByStarsHandler implements CommandHandler {

    private final RoomController roomController;

    public ListRoomsSortedByStarsHandler(RoomController roomController) {
        this.roomController = roomController;
    }
    @Override
    public void handle(Scanner scanner) {
        roomController.listRoomsSortedByStars();
    }
}
