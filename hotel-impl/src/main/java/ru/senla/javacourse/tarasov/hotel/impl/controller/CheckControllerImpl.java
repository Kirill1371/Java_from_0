package ru.senla.javacourse.tarasov.hotel.impl.controller;





import java.util.Date;
import ru.senla.javacourse.tarasov.hotel.api.controller.CheckController;
import ru.senla.javacourse.tarasov.hotel.api.dto.GuestDto;
import ru.senla.javacourse.tarasov.hotel.impl.service.HotelServiceImpl;
import ru.senla.javacourse.tarasov.hotel.ioc.annotations.Component;
import ru.senla.javacourse.tarasov.hotel.ioc.annotations.Inject;

@Component
public class CheckControllerImpl implements CheckController {
    @Inject
    private HotelServiceImpl hotelService;

    @Override
    public void checkIn(int roomNumber, GuestDto guest, Date checkInDate, Date checkOutDate) {
        hotelService.checkIn(roomNumber, guest, checkInDate, checkOutDate);
    }

    @Override
    public void checkOut(int roomNumber) {
        hotelService.checkOut(roomNumber);
    }
}

