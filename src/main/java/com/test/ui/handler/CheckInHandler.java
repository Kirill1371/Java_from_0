package com.test.ui.handler;

import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;
import java.text.ParseException;

import com.test.controller.CheckControllerIMPL;
import com.test.model.Guest;

public class CheckInHandler implements CommandHandler {

    private final CheckControllerIMPL checkController;
    private final SimpleDateFormat dateFormat;

    public CheckInHandler(CheckControllerIMPL checkController, SimpleDateFormat dateFormat) {
        this.checkController = checkController;
        this.dateFormat = dateFormat;
    }

    @Override
    public void handle(Scanner scanner) {
        System.out.println("Enter room number:");
        int roomNumber = scanner.nextInt();
        scanner.nextLine(); 
        System.out.println("Enter guest name:");
        String guestName = scanner.nextLine();
        System.out.println("Enter check-in date (yyyy-MM-dd):");
        String checkInDateStr = scanner.nextLine();
        System.out.println("Enter check-out date (yyyy-MM-dd):");
        String checkOutDateStr = scanner.nextLine();

        try {
            Date checkInDate = dateFormat.parse(checkInDateStr);
            Date checkOutDate = dateFormat.parse(checkOutDateStr);

            Guest guest = new Guest(guestName);
            checkController.checkIn(roomNumber, guest, checkInDate, checkOutDate);

            System.out.println("Guest checked in successfully.");
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
        }
    }
}
