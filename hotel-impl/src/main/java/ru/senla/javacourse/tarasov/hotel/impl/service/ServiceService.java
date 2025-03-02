package ru.senla.javacourse.tarasov.hotel.impl.service;

import ru.senla.javacourse.tarasov.hotel.api.dto.ServiceDto;
import java.util.List;

public interface ServiceService {
    void addService(String guestName, ServiceDto serviceDto);
    List<ServiceDto> getGuestServicesSortedByPrice(String guestName);
    List<ServiceDto> getGuestServicesSortedByDate(String guestName);
}