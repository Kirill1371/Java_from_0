package com.test.controller;

import java.util.Date;
import com.test.annotations.Inject;
import com.test.annotations.Component;
import com.test.model.Guest;
import com.test.service.HotelService;

@Component
public class CheckControllerIMPL implements CheckController {
    @Inject
    private HotelService hotelService;

    public void checkIn(int roomNumber, Guest guest, Date checkInDate, Date checkOutDate) {
        hotelService.checkIn(roomNumber, guest, checkInDate, checkOutDate);
    }

    public void checkOut(int roomNumber) {
        hotelService.checkOut(roomNumber);
    }
}

