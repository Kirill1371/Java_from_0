package ru.senla.javacourse.tarasov.hotel.ui.handler;


import java.util.Scanner;
import ru.senla.javacourse.tarasov.hotel.api.controller.RoomController;

public class GetTotalAvailableRoomsHandler implements CommandHandler {

    private final RoomController roomController;

    public GetTotalAvailableRoomsHandler(RoomController roomController) {
        this.roomController = roomController;
    }

    @Override
    public void handle(Scanner scanner) {
        // Получаем количество доступных комнат через контроллер
        roomController.getTotalAvailableRooms();
    }
}

