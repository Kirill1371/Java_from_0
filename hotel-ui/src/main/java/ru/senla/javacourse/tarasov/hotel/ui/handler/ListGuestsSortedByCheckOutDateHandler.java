package ru.senla.javacourse.tarasov.hotel.ui.handler;


import java.util.Scanner;
import ru.senla.javacourse.tarasov.hotel.api.controller.GuestController;

public class ListGuestsSortedByCheckOutDateHandler implements CommandHandler {

    private final GuestController guestController;

    public ListGuestsSortedByCheckOutDateHandler(GuestController guestController) {
        this.guestController = guestController;
    }
    @Override
    public void handle(Scanner scanner) {
        guestController.listGuestsSortedByCheckOutDate();
    }
}
