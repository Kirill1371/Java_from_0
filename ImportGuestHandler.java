package ui.handler;

import java.util.Scanner;

import controller.GuestControllerIMPL;

public class ImportGuestHandler implements CommandHandler{
    
    private final GuestControllerIMPL guestController;

    public ImportGuestHandler(GuestControllerIMPL guestController) {
        this.guestController = guestController;
    }


    @Override
    public void handle(Scanner scanner) {
        System.out.println("Enter file path to import rooms:");
        String filePath = scanner.nextLine();
        guestController.importGuests(filePath);
    }
    public String getCommandName() {
        return "Import Guests";
    }
}
