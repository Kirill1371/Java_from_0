package ru.senla.javacourse.tarasov.hotel.impl.controller;


import ru.senla.javacourse.tarasov.hotel.api.controller.ServiceController;
import ru.senla.javacourse.tarasov.hotel.api.dto.ServiceDto;
import ru.senla.javacourse.tarasov.hotel.db.entity.Service;
import ru.senla.javacourse.tarasov.hotel.impl.service.HotelServiceImpl;
import ru.senla.javacourse.tarasov.hotel.ioc.annotations.Component;
import ru.senla.javacourse.tarasov.hotel.ioc.annotations.Inject;

@Component
public class ServiceControllerImpl implements ServiceController {
    @Inject
    private HotelServiceImpl hotelService;

    @Inject
    public ServiceControllerImpl(HotelServiceImpl hotelService) {
        this.hotelService = hotelService;
    }

    public void addService(String guestName, ServiceDto service) {
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
