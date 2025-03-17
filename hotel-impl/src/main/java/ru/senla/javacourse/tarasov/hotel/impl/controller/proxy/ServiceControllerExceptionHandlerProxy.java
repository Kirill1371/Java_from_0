package ru.senla.javacourse.tarasov.hotel.impl.controller.proxy;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.senla.javacourse.tarasov.hotel.api.controller.ServiceController;
import ru.senla.javacourse.tarasov.hotel.api.dto.ServiceDto;

import java.util.List;

public class ServiceControllerExceptionHandlerProxy implements ServiceController {

    private final ServiceController serviceController;

    ServiceControllerExceptionHandlerProxy(ServiceController serviceController) {
        this.serviceController = serviceController; 
    }
    
    @Override
    public ResponseEntity<Void> addService(String guestName, ServiceDto service) {
        try {
            return serviceController.addService(guestName, service);
        } catch (Exception e) {
            System.out.println("404");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<List<ServiceDto>> listGuestServicesSortedByPrice(String guestName) {
        try {
            return serviceController.listGuestServicesSortedByPrice(guestName);
        } catch (Exception e) {
            System.out.println("404");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<List<ServiceDto>> listGuestServicesSortedByDate(String guestName) {
        try {
            return serviceController.listGuestServicesSortedByDate(guestName);
        } catch (Exception e) {
            System.out.println("404");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
    

