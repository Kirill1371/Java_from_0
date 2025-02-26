package ru.senla.javacourse.tarasov.hotel.impl.controller.proxy;


import ru.senla.javacourse.tarasov.hotel.api.controller.HotelController;
import ru.senla.javacourse.tarasov.hotel.api.dto.RoomDto;

public class HotelControllerExceptionHandlerProxy implements HotelController {
        
    private final HotelController target;
    public HotelControllerExceptionHandlerProxy(HotelController target) {
        this.target = target;
    }
    public void addRoom(RoomDto room) {
        try {
            target.addRoom(room);
        } catch(Exception e) {
            System.out.println("404");
        }
    } 

}
