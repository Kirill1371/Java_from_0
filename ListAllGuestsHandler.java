package ui.handler;

import java.util.Scanner;

import controller.GuestControllerIMPL;

public class ListAllGuestsHandler implements CommandHandler {

    private final GuestControllerIMPL guestController;

    public ListAllGuestsHandler(GuestControllerIMPL guestController) {
        this.guestController = guestController;
    }
    @Override
    public void handle(Scanner scanner) {
        guestController.listAllGuests();
    }
}
