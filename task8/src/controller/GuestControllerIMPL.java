package controller;

import java.util.List;

import javax.inject.Inject;

import model.Guest;
import service.IHotelService;

public class GuestControllerIMPL implements GuestContrloller{
    @Inject
    private IHotelService hotelService;

    // public GuestControllerIMPL(IHotelService hotelService) {
    //     this.hotelService = hotelService;
    // }

    public void listAllGuests() {
        List<Guest> guests = hotelService.getAllGuests();
        if (guests == null || guests.isEmpty()) {
            System.out.println("No guests found.");
        } else {
            System.out.println("List of all guests:");
            for (Guest guest : guests) {
                System.out.println("- " + guest.getName() + " Id: " + guest.getId());
            }
        }
    }

    public void listGuestsSortedByName() {
        for (Guest guest : hotelService.getGuestsSortedByName()) {
            System.out.println("Guest: " + guest.getName());
        }
    }

    public void listGuestsSortedByCheckOutDate() {
        for (Guest guest : hotelService.getGuestsSortedByCheckOutDate()) {
            System.out.println("Guest: " + guest.getName());
        }
    }

    public void getTotalGuests() {
        int totalGuests = hotelService.getTotalGuests();
        System.out.println("Total guests: " + totalGuests);
    }

    public void getTotalPaymentForGuest(String guestName) {
        double totalPayment = hotelService.getTotalPaymentForGuest(guestName);
        System.out.println("Total payment for guest " + guestName + ": " + totalPayment);
    }


    public void importGuests(String filePath) {
        try {
            hotelService.importGuestsFromCSV(filePath);
            System.out.println("Guests imported successfully from: " + filePath);
        } catch (Exception e) {
            System.out.println("Failed to import guests: " + e.getMessage());
        }
    }

    public void exportGuests(String filePath) {
        try {
            hotelService.exportGuestsToCSV(filePath);
            System.out.println("Guests exported successfully to: " + filePath);
        } catch (Exception e) {
            System.out.println("Failed to export guests: " + e.getMessage());
        }
    }

    
}
