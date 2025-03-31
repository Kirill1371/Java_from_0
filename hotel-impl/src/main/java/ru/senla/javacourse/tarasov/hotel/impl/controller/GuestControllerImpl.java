package ru.senla.javacourse.tarasov.hotel.impl.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.senla.javacourse.tarasov.hotel.api.controller.GuestController;
import ru.senla.javacourse.tarasov.hotel.api.dto.GuestDto;
import ru.senla.javacourse.tarasov.hotel.impl.service.GuestService;

@RestController
@RequestMapping("/guests")
public class GuestControllerImpl implements GuestController {

    private GuestService guestService;

    @Autowired // Внедрение зависимости через конструктор
    public GuestControllerImpl(GuestService guestService) {
        this.guestService = guestService;
    }

    @GetMapping
    public ResponseEntity<List<GuestDto>> listAllGuests() {
        List<GuestDto> guests = guestService.getAllGuests();
        if (guests == null || guests.isEmpty()) {
            System.out.println("No guests found.");
        } else {
            System.out.println("List of all guests:");
            for (GuestDto guest : guests) {
                System.out.println("- " + guest.getName() + " (ID: " + guest.getId() + ")");
            }
        }
        return new ResponseEntity<>(guests, HttpStatus.OK);
    }

    @GetMapping("/by/name")
    public ResponseEntity<List<GuestDto>> listGuestsSortedByName() {
        List<GuestDto> guests = guestService.getGuestsSortedByName();
        if (guests.isEmpty()) {
            System.out.println("No guests found.");
        } else {
            System.out.println("Guests sorted by name:");
            for (GuestDto guest : guests) {
                System.out.println("- " + guest.getName());
            }
        }
        return new ResponseEntity<>(guests, HttpStatus.OK);
    }

    @GetMapping("/by/out")
    public ResponseEntity<List<GuestDto>> listGuestsSortedByCheckOutDate() {
        List<GuestDto> guests = guestService.getGuestsSortedByCheckOutDate();
        if (guests.isEmpty()) {
            System.out.println("No guests found.");
        } else {
            System.out.println("Guests sorted by check-out date:");
            for (GuestDto guest : guests) {
                System.out.println("- " + guest.getName());
            }
        }
        return new ResponseEntity<>(guests, HttpStatus.OK);
    }

    @GetMapping("/all/total")
    public ResponseEntity<Integer> getTotalGuests() {
        int totalGuests = guestService.getTotalGuests();
        System.out.println("Total guests: " + totalGuests);
        return new ResponseEntity<> (totalGuests, HttpStatus.OK);
    }

    @GetMapping("/{guestName}/payment")
    public ResponseEntity<Double> getTotalPaymentForGuest(@PathVariable("guestName") String guestName) {
        double totalPayment = guestService.getTotalPaymentForGuest(guestName);
        System.out.println("Total payment for guest " + guestName + ": " + totalPayment);
        return new ResponseEntity<> (totalPayment, HttpStatus.OK);
    }
}