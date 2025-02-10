package com.test.controller;

import java.util.Date;

import com.test.model.Guest;

public interface CheckController {
    public void checkIn(int roomNumber, Guest guest, Date checkInDate, Date checkOutDate);
    public void checkOut(int roomNumber);
}
