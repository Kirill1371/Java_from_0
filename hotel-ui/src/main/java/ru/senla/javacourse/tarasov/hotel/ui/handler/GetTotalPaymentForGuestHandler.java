package ru.senla.javacourse.tarasov.hotel.ui.handler;


import java.util.Scanner;
import ru.senla.javacourse.tarasov.hotel.api.controller.GuestController;

public class GetTotalPaymentForGuestHandler implements CommandHandler {

    private final GuestController guestController;

    public GetTotalPaymentForGuestHandler(GuestController guestController) {
        this.guestController = guestController;
    }
    @Override
    public void handle(Scanner scanner) {
        System.out.println("Enter guest name:");
        String guestName = scanner.nextLine();
        guestController.getTotalPaymentForGuest(guestName);
    }
}
