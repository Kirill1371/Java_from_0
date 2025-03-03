package ru.senla.javacourse.tarasov.hotel.ui.handler;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import ru.senla.javacourse.tarasov.hotel.api.controller.RoomController;

public class ListRoomsAvailableByDateHandler implements CommandHandler {

    private final RoomController roomController;
    private final SimpleDateFormat dateFormat;

    public ListRoomsAvailableByDateHandler(RoomController roomController, SimpleDateFormat dateFormat) {
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