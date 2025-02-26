package ru.senla.javacourse.tarasov.hotel.impl.controller.proxy;


import java.util.Date;
import ru.senla.javacourse.tarasov.hotel.api.controller.CheckController;
import ru.senla.javacourse.tarasov.hotel.api.dto.GuestDto;


public class CheckControllerExceptionHandlerProxy implements CheckController {

    private final CheckController checkController;
    public CheckControllerExceptionHandlerProxy(CheckController checkController) {
        this.checkController = checkController;
    }

    public void checkIn(int roomNumber, GuestDto guest, Date checkInDate, Date checkOutDate) {
        try {
            checkController.checkIn(roomNumber, guest, checkInDate, checkOutDate);
        } catch(Exception e) {
            System.out.println("404");
        }
    }

    public void checkOut(int roomNumber) {
        try {
            checkController.checkOut(roomNumber);
        } catch(Exception e) {
            System.out.println("404");
        }
    }
}
