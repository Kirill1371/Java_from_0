package com.test.ui.handler;

import java.util.Scanner;

import com.test.controller.GuestControllerIMPL;

public class GetTotalPaymentForGuestHandler implements CommandHandler {

    private final GuestControllerIMPL guestController;

    public GetTotalPaymentForGuestHandler(GuestControllerIMPL guestController) {
        this.guestController = guestController;
    }
    @Override
    public void handle(Scanner scanner) {
        System.out.println("Enter guest name:");
        String guestName = scanner.nextLine();
        guestController.getTotalPaymentForGuest(guestName);
    }
}
