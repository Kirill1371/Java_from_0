package ru.senla.javacourse.tarasov.hotel.impl.controller.proxy;


import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.senla.javacourse.tarasov.hotel.api.controller.CheckController;
import ru.senla.javacourse.tarasov.hotel.api.dto.GuestDto;


public class CheckControllerExceptionHandlerProxy implements CheckController {

    private final CheckController checkController;
    public CheckControllerExceptionHandlerProxy(CheckController checkController) {
        this.checkController = checkController;
    }

    @Override
    public ResponseEntity<Void> checkIn(int roomNumber, GuestDto guest, Date checkInDate, Date checkOutDate) {
        try {
            return checkController.checkIn(roomNumber, guest, checkInDate, checkOutDate);
        } catch(Exception e) {
            System.out.println("404");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<Void> checkOut(int roomNumber) {
        try {
            return checkController.checkOut(roomNumber);
        } catch(Exception e) {
            System.out.println("404");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
