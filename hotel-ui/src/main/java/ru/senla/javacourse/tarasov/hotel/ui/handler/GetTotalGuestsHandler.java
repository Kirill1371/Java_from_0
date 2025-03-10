package ru.senla.javacourse.tarasov.hotel.ui.handler;


import java.util.Scanner;
import ru.senla.javacourse.tarasov.hotel.api.controller.GuestController;

public class GetTotalGuestsHandler implements CommandHandler {

    private final GuestController guestController;

    public GetTotalGuestsHandler(GuestController guestController) {
        this.guestController = guestController;
    }

    @Override
    public void handle(Scanner scanner) {
        guestController.getTotalGuests();
    }
}
