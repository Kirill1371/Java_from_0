package controller;

import java.util.Date;
import annotations.Inject;
import annotations.Component;
import model.Guest;
import service.HotelService;

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


//package controller;
//
//import java.util.Date;
//
//import annotations.Inject1;
//
//import annotations.Component;
//import model.Guest;
//import service.HotelService;
//import service.IHotelService;
//
//@Component
//public class CheckControllerIMPL implements CheckController{
//    @Inject1
//    private HotelService hotelService;
//
//    @Inject1
//    public CheckControllerIMPL(HotelService hotelService) {
//        this.hotelService = hotelService;
//    }
//
//
//    public void checkIn(int roomNumber, Guest guest, Date checkInDate, Date checkOutDate) {
//        hotelService.checkIn(roomNumber, guest, checkInDate, checkOutDate);
//    }
//
//    public void checkOut(int roomNumber) {
//        hotelService.checkOut(roomNumber);
//    }
//}
