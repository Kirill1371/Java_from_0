package ru.senla.javacourse.tarasov.hotel.api.controller;


import java.util.Date;

import org.springframework.http.ResponseEntity;
import ru.senla.javacourse.tarasov.hotel.api.dto.GuestDto;

public interface CheckController {
    ResponseEntity<Void> checkIn(int roomNumber, GuestDto guest, Date checkInDate, Date checkOutDate);
    ResponseEntity<Void> checkOut(int roomNumber);
}
