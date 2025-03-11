package controller;

import java.util.Date;
import model.Guest;


public class CheckControllerProxy implements CheckController{

    private final CheckController checkController;
    CheckControllerProxy (CheckController checkController) {
        this.checkController = checkController;
    }

    public void checkIn(int roomNumber, Guest guest, Date checkInDate, Date checkOutDate) {
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
