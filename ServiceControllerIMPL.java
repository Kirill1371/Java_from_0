package controller;

import model.Service;
import service.IHotelService;

public class ServiceControllerIMPL implements ServiceController{

    private IHotelService hotelService;

    public ServiceControllerIMPL(IHotelService hotelService) {
        this.hotelService = hotelService;
    }

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
