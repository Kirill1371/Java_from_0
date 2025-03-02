package ru.senla.javacourse.tarasov.hotel.impl.controller;

import ru.senla.javacourse.tarasov.hotel.api.controller.CheckController;
import ru.senla.javacourse.tarasov.hotel.api.dto.GuestDto;
import ru.senla.javacourse.tarasov.hotel.api.dto.StayDto;
import ru.senla.javacourse.tarasov.hotel.impl.mapper.GuestMapper;
import ru.senla.javacourse.tarasov.hotel.impl.mapper.StayMapper;
import ru.senla.javacourse.tarasov.hotel.impl.service.CheckServiceImpl;
import ru.senla.javacourse.tarasov.hotel.ioc.annotations.Component;
import ru.senla.javacourse.tarasov.hotel.ioc.annotations.Inject;

import java.util.Date;

@Component
public class CheckControllerImpl implements CheckController {

    @Inject
    private CheckServiceImpl checkService;

    @Override
    public void checkIn(int roomNumber, GuestDto guestDto, Date checkInDate, Date checkOutDate) {
        // Вызываем сервис с правильными параметрами
        checkService.checkIn(roomNumber, guestDto, checkInDate, checkOutDate);
    }

    @Override
    public void checkOut(int roomNumber) {
        checkService.checkOut(roomNumber);
    }
}