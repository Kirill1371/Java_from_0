package controller;

import java.util.Date;

import model.Guest;

public interface CheckController {
    public void checkIn(int roomNumber, Guest guest, Date checkInDate, Date checkOutDate);
    public void checkOut(int roomNumber);
}
