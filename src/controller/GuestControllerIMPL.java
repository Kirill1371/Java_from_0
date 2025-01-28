package controller;

import java.util.List;

import annotations.Component;
import annotations.Inject1;
import model.Guest;
import service.HotelService;
import service.IHotelService;


@Component
public class GuestControllerIMPL implements GuestController{
    @Inject1
    private HotelService hotelService;

    @Inject1
    public GuestControllerIMPL(HotelService hotelService) {
        this.hotelService = hotelService;
    }

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
}
