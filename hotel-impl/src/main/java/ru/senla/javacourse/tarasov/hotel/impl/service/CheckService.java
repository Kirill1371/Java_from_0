package ru.senla.javacourse.tarasov.hotel.impl.service;

import ru.senla.javacourse.tarasov.hotel.api.dto.GuestDto;
import java.util.Date;

public interface CheckService {
    void checkIn(int roomNumber, GuestDto guestDto, Date checkInDate, Date checkOutDate);
    void checkOut(int roomNumber);
}