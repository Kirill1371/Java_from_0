package controller;

import java.util.Date;

import javax.inject.Inject;

import model.Guest;
import service.IHotelService;

public class CheckControllerIMPL implements CheckController{
    @Inject
    private IHotelService hotelService;

    // public CheckControllerIMPL(IHotelService hotelService) {
    //     this.hotelService = hotelService;
    // }


    public void checkIn(int roomNumber, Guest guest, Date checkInDate, Date checkOutDate) {
        hotelService.checkIn(roomNumber, guest, checkInDate, checkOutDate);
    }

    public void checkOut(int roomNumber) {
        hotelService.checkOut(roomNumber);
    }
}
