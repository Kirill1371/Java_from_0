package ru.senla.javacourse.tarasov.hotel.impl.service;

import java.util.List;
import ru.senla.javacourse.tarasov.hotel.api.dto.GuestDto;

public interface GuestService {
    List<GuestDto> getAllGuests();
    List<GuestDto> getGuestsSortedByName();
    List<GuestDto> getGuestsSortedByCheckOutDate();
    int getTotalGuests();
    double getTotalPaymentForGuest(String guestName);
}