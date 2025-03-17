package ru.senla.javacourse.tarasov.hotel.impl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.senla.javacourse.tarasov.hotel.api.controller.ServiceController;
import ru.senla.javacourse.tarasov.hotel.api.dto.ServiceDto;
import ru.senla.javacourse.tarasov.hotel.impl.service.ServiceService;

@RestController
@RequestMapping("/service")
public class ServiceControllerImpl implements ServiceController {

    private ServiceService serviceService;

    @Autowired // Внедрение зависимости через конструктор
    public ServiceControllerImpl(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @PostMapping
    @Override
    public ResponseEntity<Void> addService(String guestName, ServiceDto serviceDto) {
        serviceService.addService(guestName, serviceDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping("/{guestName}/by/price")
    @Override
    public ResponseEntity<List<ServiceDto>> listGuestServicesSortedByPrice(@PathVariable("guestName") String guestName) {
        List<ServiceDto> services = serviceService.getGuestServicesSortedByPrice(guestName);
        if (services.isEmpty()) {
            System.out.println("No services found for guest: " + guestName);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            System.out.println("Services for guest " + guestName + " sorted by price:");
            services.forEach(service ->
                    System.out.println("- " + service.getName() + ", Price: " + service.getPrice()));
            return new ResponseEntity<>(services, HttpStatus.OK);
        }
    }

    @GetMapping("/by/date/{guestName}")
    @Override
    public ResponseEntity<List<ServiceDto>> listGuestServicesSortedByDate(@PathVariable("guestName") String guestName) {
        List<ServiceDto> services = serviceService.getGuestServicesSortedByDate(guestName);
        if (services.isEmpty()) {
            System.out.println("No services found for guest: " + guestName);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            System.out.println("Services for guest " + guestName + " sorted by date:");
            services.forEach(service ->
                    System.out.println("- " + service.getName() + ", Date: " + service.getDate()));
            return new ResponseEntity<>(services, HttpStatus.OK);
        }
    }
}
