package ui.handler;

import java.util.Scanner;

import controller.GuestControllerIMPL;

public class GetTotalGuestsHandler implements CommandHandler {

    private final GuestControllerIMPL guestController;

    public GetTotalGuestsHandler(GuestControllerIMPL guestController) {
        this.guestController = guestController;
    }

    @Override
    public void handle(Scanner scanner) {
        guestController.getTotalGuests();
    }
}
