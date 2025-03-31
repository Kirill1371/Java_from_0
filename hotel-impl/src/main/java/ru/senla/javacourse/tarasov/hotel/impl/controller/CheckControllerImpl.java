package ru.senla.javacourse.tarasov.hotel.impl.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.senla.javacourse.tarasov.hotel.api.controller.CheckController;
import ru.senla.javacourse.tarasov.hotel.api.dto.GuestDto;
import ru.senla.javacourse.tarasov.hotel.impl.service.CheckService;
import ru.senla.javacourse.tarasov.hotel.ioc.annotations.Component;
import ru.senla.javacourse.tarasov.hotel.ioc.annotations.Inject;

@RestController
@RequestMapping("/check")
public class CheckControllerImpl implements CheckController {

    private CheckService checkService;

    @Autowired
    public CheckControllerImpl(CheckService checkService) {
        this.checkService = checkService;
    }

    @PostMapping("/in")
    @Override
    public ResponseEntity<Void> checkIn(int roomNumber, GuestDto guestDto, Date checkInDate, Date checkOutDate) {
        // Вызываем сервис с правильными параметрами
        checkService.checkIn(roomNumber, guestDto, checkInDate, checkOutDate);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/out")
    @Override
    public ResponseEntity<Void> checkOut(int roomNumber) {
        checkService.checkOut(roomNumber);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}