package ru.senla.javacourse.tarasov.hotel.impl.controller;

import java.util.Date;
import ru.senla.javacourse.tarasov.hotel.api.controller.CheckController;
import ru.senla.javacourse.tarasov.hotel.api.dto.GuestDto;
import ru.senla.javacourse.tarasov.hotel.impl.service.CheckService;
import ru.senla.javacourse.tarasov.hotel.ioc.annotations.Component;
import ru.senla.javacourse.tarasov.hotel.ioc.annotations.Inject;

@Component
public class CheckControllerImpl implements CheckController {

    @Inject
    private CheckService checkService;

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