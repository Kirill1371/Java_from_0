package ui.handler;

import java.util.Scanner;

import controller.GuestControllerIMPL;

public class ExportGuestHandler implements CommandHandler {

    private final GuestControllerIMPL guestController;

    public ExportGuestHandler(GuestControllerIMPL guestController) {
        this.guestController = guestController;
    }

    @Override
    public void handle(Scanner scanner) {
        System.out.println("Enter file path to export guests:");
        String filePath = scanner.nextLine();
        
        try {
            guestController.exportGuests(filePath);
            System.out.println("Guests successfully exported to: " + filePath);
        } catch (Exception e) {
            System.out.println("Error exporting guests: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
