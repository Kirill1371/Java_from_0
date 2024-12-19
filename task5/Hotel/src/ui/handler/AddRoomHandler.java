package ui.handler;

import java.util.Scanner;
import controller.RoomControllerIMPL;
import model.Room;

public class AddRoomHandler implements CommandHandler {
    private final RoomControllerIMPL roomController;

    public AddRoomHandler(RoomControllerIMPL roomController) {
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

        Room newRoom = new Room(0, roomNumber, "Available", price, capacity, stars);
        roomController.addRoom(newRoom);
        System.out.println("Room added successfully.");
    }
}
