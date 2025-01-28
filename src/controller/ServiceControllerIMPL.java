package controller;

import annotations.Inject1;

import annotations.Component;
import model.Service;
import service.HotelService;

@Component
public class ServiceControllerIMPL implements ServiceController{
    @Inject1
    private HotelService hotelService;

    @Inject1
    public ServiceControllerIMPL(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    public void addService(String guestName, Service service) {
        hotelService.addService(guestName, service);
    }

    public void listGuestServicesSortedByPrice(String guestName) {
        for (Service service : hotelService.getGuestServicesSortedByPrice(guestName)) {
            System.out.println("Service: " + service.getName() + ", Price: " + service.getPrice());
        }
    }

    public void listGuestServicesSortedByDate(String guestName) {
        for (Service service : hotelService.getGuestServicesSortedByDate(guestName)) {
            System.out.println("Service: " + service.getName() + ", Date: " + service.getDate());
        }
    }
}
