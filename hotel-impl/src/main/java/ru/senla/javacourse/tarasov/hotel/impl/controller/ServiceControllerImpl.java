package ru.senla.javacourse.tarasov.hotel.impl.controller;

import java.util.List;
import ru.senla.javacourse.tarasov.hotel.api.controller.ServiceController;
import ru.senla.javacourse.tarasov.hotel.api.dto.ServiceDto;
import ru.senla.javacourse.tarasov.hotel.impl.service.ServiceService;
import ru.senla.javacourse.tarasov.hotel.ioc.annotations.Component;
import ru.senla.javacourse.tarasov.hotel.ioc.annotations.Inject;

@Component
public class ServiceControllerImpl implements ServiceController {

    @Inject
    private ServiceService serviceService;

    @Override
    public void addService(String guestName, ServiceDto serviceDto) {
        serviceService.addService(guestName, serviceDto);
    }


    @Override
    public void listGuestServicesSortedByPrice(String guestName) {
        List<ServiceDto> services = serviceService.getGuestServicesSortedByPrice(guestName);
        if (services.isEmpty()) {
            System.out.println("No services found for guest: " + guestName);
        } else {
            System.out.println("Services for guest " + guestName + " sorted by price:");
            services.forEach(service ->
                    System.out.println("- " + service.getName() + ", Price: " + service.getPrice()));
        }
    }

    @Override
    public void listGuestServicesSortedByDate(String guestName) {
        List<ServiceDto> services = serviceService.getGuestServicesSortedByDate(guestName);
        if (services.isEmpty()) {
            System.out.println("No services found for guest: " + guestName);
        } else {
            System.out.println("Services for guest " + guestName + " sorted by date:");
            services.forEach(service ->
                    System.out.println("- " + service.getName() + ", Date: " + service.getDate()));
        }
    }
}
