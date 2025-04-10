package ui.handler;

import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;
import java.text.ParseException;

import controller.RoomControllerIMPL;

public class ListRoomsAvailableByDateHandler implements CommandHandler {

    private final RoomControllerIMPL roomController;
    private final SimpleDateFormat dateFormat;

    public ListRoomsAvailableByDateHandler(RoomControllerIMPL roomController, SimpleDateFormat dateFormat) {
        this.roomController = roomController;
        this.dateFormat = dateFormat;
    }

    @Override
    public void handle(Scanner scanner) {
        System.out.println("Enter date (yyyy-MM-dd):");
        String dateStr = scanner.nextLine();
        try {
            Date date = dateFormat.parse(dateStr);
            roomController.listRoomsAvailableByDate(date);
        } catch (ParseException e) {
            System.out.
            println("Invalid date format. Please use yyyy-MM-dd.");
        }
    }
}