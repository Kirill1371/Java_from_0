package ru.senla.javacourse.tarasov.hotel.api.controller;


import org.springframework.http.ResponseEntity;
import ru.senla.javacourse.tarasov.hotel.api.dto.ServiceDto;

import java.util.List;

public interface ServiceController {
    public ResponseEntity<Void> addService(String guestName, ServiceDto service);
    public ResponseEntity<List<ServiceDto>> listGuestServicesSortedByPrice(String guestName);
    public ResponseEntity<List<ServiceDto>> listGuestServicesSortedByDate(String guestName);
} 