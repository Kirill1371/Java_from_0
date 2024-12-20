package controller;

import model.Room;

public class HotelControllerDec implements HotelController {
        
    private final HotelController target;
    HotelControllerDec (HotelController target) {
        this.target = target;
    }
    public void addRoom(Room room) {
        try {
            target.addRoom(room);
        } catch(Exception e) {
            System.out.println("404");
        }
    } 

}
