package com.test.ui.handler;

import java.util.Scanner;

import com.test.controller.GuestControllerIMPL;

public class ListGuestsSortedByNameHandler implements CommandHandler {

    private final GuestControllerIMPL guestController;

    public ListGuestsSortedByNameHandler(GuestControllerIMPL guestController) {
        this.guestController = guestController;
    }

    @Override
    public void handle(Scanner scanner) {
        guestController.listGuestsSortedByName();
    }
}