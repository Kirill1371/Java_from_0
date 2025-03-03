package ru.senla.javacourse.tarasov.hotel.impl.service;

import java.util.List;
import ru.senla.javacourse.tarasov.hotel.api.dto.ServiceDto;

public interface ServiceService {
    void addService(String guestName, ServiceDto serviceDto);
    List<ServiceDto> getGuestServicesSortedByPrice(String guestName);
    List<ServiceDto> getGuestServicesSortedByDate(String guestName);
}