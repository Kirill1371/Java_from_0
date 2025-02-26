package ru.senla.javacourse.tarasov.hotel.ui.handler;


import java.util.Scanner;
import ru.senla.javacourse.tarasov.hotel.impl.controller.GuestControllerImpl;

public class ListGuestsSortedByCheckOutDateHandler implements CommandHandler {

    private final GuestControllerImpl guestController;

    public ListGuestsSortedByCheckOutDateHandler(GuestControllerImpl guestController) {
        this.guestController = guestController;
    }
    @Override
    public void handle(Scanner scanner) {
        guestController.listGuestsSortedByCheckOutDate();
    }
}
