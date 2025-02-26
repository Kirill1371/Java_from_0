package ru.senla.javacourse.tarasov.hotel.impl.controller.proxy;


import ru.senla.javacourse.tarasov.hotel.api.controller.ServiceController;
import ru.senla.javacourse.tarasov.hotel.api.dto.ServiceDto;

public class ServiceControllerExceptionHandlerProxy implements ServiceController {

    private final ServiceController serviceController;

    ServiceControllerExceptionHandlerProxy(ServiceController serviceController) {
        this.serviceController = serviceController; 
    }
    
    
    public void addService(String guestName, ServiceDto service) {
        try {
            serviceController.addService(guestName, service);
        } catch (Exception e) {
            System.out.println("404");
        }
    }

    public void listGuestServicesSortedByPrice(String guestName) {
        try {
            serviceController.listGuestServicesSortedByPrice(guestName);
        } catch (Exception e) {
            System.out.println("404");
        }
    }

    public void listGuestServicesSortedByDate(String guestName) {
        try {
            serviceController.listGuestServicesSortedByDate(guestName);
        } catch (Exception e) {
            System.out.println("404");
        }
    }
}
    

