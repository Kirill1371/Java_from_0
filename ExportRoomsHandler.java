package ui.handler;

import java.util.Scanner;

import controller.RoomControllerIMPL;

public class ExportRoomsHandler implements CommandHandler {

    private final RoomControllerIMPL roomController;

    public ExportRoomsHandler(RoomControllerIMPL roomController) {
        this.roomController = roomController;
    }

    @Override
    public void handle(Scanner scanner) {
        System.out.println("Enter file path to export rooms:");
        String filePath = scanner.nextLine();
        roomController.exportRooms(filePath);
    }
    public String getCommandName() {
        return "Import Rooms";
    }
}
