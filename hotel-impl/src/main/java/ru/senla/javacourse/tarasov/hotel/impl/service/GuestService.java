package ru.senla.javacourse.tarasov.hotel.impl.service;

import ru.senla.javacourse.tarasov.hotel.api.dto.GuestDto;
import java.util.List;

public interface GuestService {
    List<GuestDto> getAllGuests();
    List<GuestDto> getGuestsSortedByName();
    List<GuestDto> getGuestsSortedByCheckOutDate();
    int getTotalGuests();
    double getTotalPaymentForGuest(String guestName);
}