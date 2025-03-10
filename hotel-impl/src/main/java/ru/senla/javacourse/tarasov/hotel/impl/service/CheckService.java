package ru.senla.javacourse.tarasov.hotel.impl.service;

import java.util.Date;
import ru.senla.javacourse.tarasov.hotel.api.dto.GuestDto;

public interface CheckService {
    void checkIn(int roomNumber, GuestDto guestDto, Date checkInDate, Date checkOutDate);
    void checkOut(int roomNumber);
}