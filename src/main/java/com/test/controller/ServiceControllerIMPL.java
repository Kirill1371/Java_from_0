package com.test.controller;

import com.test.annotations.Inject;

import com.test.annotations.Component;
import com.test.model.Service;
import com.test.service.HotelService;

@Component
public class ServiceControllerIMPL implements ServiceController{
    @Inject
    private HotelService hotelService;

    @Inject
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
