package ui.handler;

import java.util.Scanner;

import controller.GuestControllerIMPL;

public class ListGuestsSortedByCheckOutDateHandler implements CommandHandler {

    private final GuestControllerIMPL guestController;

    public ListGuestsSortedByCheckOutDateHandler(GuestControllerIMPL guestController) {
        this.guestController = guestController;
    }
    @Override
    public void handle(Scanner scanner) {
        guestController.listGuestsSortedByCheckOutDate();
    }
}
