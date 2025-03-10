package ru.senla.javacourse.tarasov.hotel.api.controller;


import java.util.Date;
import ru.senla.javacourse.tarasov.hotel.api.dto.GuestDto;

public interface CheckController {
    public void checkIn(int roomNumber, GuestDto guest, Date checkInDate, Date checkOutDate);
    public void checkOut(int roomNumber);
}
