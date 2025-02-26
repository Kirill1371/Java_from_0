package ru.senla.javacourse.tarasov.hotel.api.controller;


import ru.senla.javacourse.tarasov.hotel.api.dto.ServiceDto;

public interface ServiceController {
    public void addService(String guestName, ServiceDto service);
    public void listGuestServicesSortedByPrice(String guestName);
    public void listGuestServicesSortedByDate(String guestName);
} 