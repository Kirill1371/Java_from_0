package ru.senla.javacourse.tarasov.hotel.impl.controller;

import ru.senla.javacourse.tarasov.hotel.api.controller.GuestController;
import ru.senla.javacourse.tarasov.hotel.api.dto.GuestDto;
import ru.senla.javacourse.tarasov.hotel.impl.mapper.GuestMapper;
import ru.senla.javacourse.tarasov.hotel.impl.service.GuestServiceImpl;
import ru.senla.javacourse.tarasov.hotel.impl.service.HotelServiceImpl;
import ru.senla.javacourse.tarasov.hotel.ioc.annotations.Component;
import ru.senla.javacourse.tarasov.hotel.ioc.annotations.Inject;

import java.util.List;

@Component
public class GuestControllerImpl implements GuestController {

    @Inject
    private GuestServiceImpl guestService;

    @Inject
    public GuestControllerImpl(GuestServiceImpl guestService) {
        this.guestService = guestService;
    }

    @Override
    public void listAllGuests() {
        List<GuestDto> guests = guestService.getAllGuests();
        if (guests == null || guests.isEmpty()) {
            System.out.println("No guests found.");
        } else {
            System.out.println("List of all guests:");
            for (GuestDto guest : guests) {
                System.out.println("- " + guest.getName() + " (ID: " + guest.getId() + ")");
            }
        }
    }

    @Override
    public void listGuestsSortedByName() {
        List<GuestDto> guests = guestService.getGuestsSortedByName();
        if (guests.isEmpty()) {
            System.out.println("No guests found.");
        } else {
            System.out.println("Guests sorted by name:");
            for (GuestDto guest : guests) {
                System.out.println("- " + guest.getName());
            }
        }
    }

    @Override
    public void listGuestsSortedByCheckOutDate() {
        List<GuestDto> guests = guestService.getGuestsSortedByCheckOutDate();
        if (guests.isEmpty()) {
            System.out.println("No guests found.");
        } else {
            System.out.println("Guests sorted by check-out date:");
            for (GuestDto guest : guests) {
                System.out.println("- " + guest.getName());
            }
        }
    }

    @Override
    public void getTotalGuests() {
        int totalGuests = guestService.getTotalGuests();
        System.out.println("Total guests: " + totalGuests);
    }

    @Override
    public void getTotalPaymentForGuest(String guestName) {
        double totalPayment = guestService.getTotalPaymentForGuest(guestName);
        System.out.println("Total payment for guest " + guestName + ": " + totalPayment);
    }
}