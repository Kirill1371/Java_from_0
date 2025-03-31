package ui.handler;

import java.util.Scanner;

import controller.RoomControllerIMPL;

public class ImportRoomsHandler implements CommandHandler {

    private final RoomControllerIMPL roomController;

    public ImportRoomsHandler(RoomControllerIMPL roomController) {
        this.roomController = roomController;
    }


    @Override
    public void handle(Scanner scanner) {
        System.out.println("Enter file path to import rooms:");
        String filePath = scanner.nextLine();
        roomController.importRooms(filePath);
    }
    public String getCommandName() {
        return "Import Rooms";
    }
}
