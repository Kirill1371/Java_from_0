package ru.senla.javacourse.tarasov.hotel.api.controller;

import org.springframework.http.ResponseEntity;
import ru.senla.javacourse.tarasov.hotel.api.dto.GuestDto;

import java.util.List;

public interface GuestController {
    ResponseEntity<List<GuestDto>> listAllGuests();
    ResponseEntity<List<GuestDto>> listGuestsSortedByName();
    ResponseEntity<List<GuestDto>> listGuestsSortedByCheckOutDate();
    ResponseEntity<Integer> getTotalGuests();
    ResponseEntity<Double> getTotalPaymentForGuest(String guestName);
} 