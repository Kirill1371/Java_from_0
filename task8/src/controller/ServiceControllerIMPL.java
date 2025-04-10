package controller;

import annotations.Inject1;

import annotations.Component;
import model.Service;
import service.HotelService;
import service.IHotelService;

@Component
public class ServiceControllerIMPL implements ServiceController{
    @Inject1
    private HotelService hotelService;

    @Inject1
    public ServiceControllerIMPL(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    // public ServiceControllerIMPL() {
    //     // Конструктор по умолчанию
    // }

    public void addService(Service service) {
        hotelService.addService(service);
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

    public void listServicesSortedByCategoryAndPrice() {
        for (Service service : hotelService.getServicesSortedByCategoryAndPrice()) {
            System.out.println("Service: " + service.getName() + ", Category: " + service.getCategory() + ", Price: " + service.getPrice()  + ", Id: " + service.getId());
        }
    }
}
