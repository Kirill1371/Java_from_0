package ru.senla.javacourse.tarasov.hotel.impl.repository;

import ru.senla.javacourse.tarasov.hotel.db.entity.Service;

import java.util.List;

public interface ServiceRepository {
    void addService(String guestName, Service service);
    List<Service> getServicesForGuest(String guestName);
}
