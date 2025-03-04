package ru.senla.javacourse.tarasov.hotel.ui.handler;


import java.util.Scanner;
import ru.senla.javacourse.tarasov.hotel.api.controller.RoomController;
import ru.senla.javacourse.tarasov.hotel.api.dto.RoomDto;


public class AddRoomHandler implements CommandHandler {

    private final RoomController roomController;

    public AddRoomHandler(RoomController roomController) {
        this.roomController = roomController;
    }

    @Override
    public void handle(Scanner scanner) {
        System.out.println("Enter room number:");
        int roomNumber = scanner.nextInt();
        System.out.println("Enter room price:");
        double price = scanner.nextDouble();
        System.out.println("Enter room capacity:");
        int capacity = scanner.nextInt();
        System.out.println("Enter room stars:");
        int stars = scanner.nextInt();

        RoomDto roomDto = new RoomDto(roomNumber, price, capacity, stars);
        // Добавляем комнату через RoomControllerIMPL
        roomController.addRoom(roomDto);
        System.out.println("Room added successfully.");
    }
}
