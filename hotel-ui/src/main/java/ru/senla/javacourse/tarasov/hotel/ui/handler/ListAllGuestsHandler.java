package ru.senla.javacourse.tarasov.hotel.ui.handler;


import java.util.Scanner;
import ru.senla.javacourse.tarasov.hotel.api.controller.GuestController;

public class ListAllGuestsHandler implements CommandHandler {

    private final GuestController guestController;

    public ListAllGuestsHandler(GuestController guestController) {
        this.guestController = guestController;
    }
    @Override
    public void handle(Scanner scanner) {
        guestController.listAllGuests();
    }
}
