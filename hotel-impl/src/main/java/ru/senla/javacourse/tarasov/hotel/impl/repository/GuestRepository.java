package ru.senla.javacourse.tarasov.hotel.impl.repository;

import ru.senla.javacourse.tarasov.hotel.api.dto.GuestDto;
import ru.senla.javacourse.tarasov.hotel.db.entity.Guest;

import java.util.List;

public interface GuestRepository {
    Guest getGuest(String name);
    List<Guest> getAllGuests();
    void addGuest(Guest guest);
    Guest getGuestByName(String name);
    public List<Guest> getAllGuestsWithStays();
    public List<Guest> getAllGuestsWithStaysAndServices();
}
