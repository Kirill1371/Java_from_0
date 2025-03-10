package ru.senla.javacourse.tarasov.hotel.impl.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import ru.senla.javacourse.tarasov.hotel.api.dto.ServiceDto;
import ru.senla.javacourse.tarasov.hotel.db.entity.Guest;
import ru.senla.javacourse.tarasov.hotel.db.entity.Service;
import ru.senla.javacourse.tarasov.hotel.impl.mapper.ServiceMapper;
//import ru.senla.javacourse.tarasov.hotel.impl.repository.HotelRepository;
import ru.senla.javacourse.tarasov.hotel.impl.repository.GuestRepository;
import ru.senla.javacourse.tarasov.hotel.impl.repository.ServiceRepository;
import ru.senla.javacourse.tarasov.hotel.impl.service.ServiceService;
import ru.senla.javacourse.tarasov.hotel.ioc.annotations.Component;
import ru.senla.javacourse.tarasov.hotel.ioc.annotations.Inject;

@Component
public class ServiceServiceImpl implements ServiceService {

    @Inject
    private ServiceRepository serviceRepository;
    private GuestRepository guestRepository;

    @Override
    public void addService(String guestName, ServiceDto serviceDto) {
        Service service = ServiceMapper.toEntity(serviceDto);
        serviceRepository.addService(guestName, service);
        System.out.println("Service added for guest: " + guestName + ", Service: " + service.getName());
    }

    @Override
    public List<ServiceDto> getGuestServicesSortedByPrice(String guestName) {
        Guest guest = guestRepository.getGuest(guestName);
        if (guest == null) {
            return new ArrayList<>();
        }
        return guest.getServices().stream()
                .sorted((s1, s2) -> Double.compare(s1.getPrice(), s2.getPrice()))
                .map(ServiceMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ServiceDto> getGuestServicesSortedByDate(String guestName) {
        Guest guest = guestRepository.getGuest(guestName);
        if (guest == null) {
            return new ArrayList<>();
        }
        return guest.getServices().stream()
                .sorted((s1, s2) -> s1.getDate().compareTo(s2.getDate()))
                .map(ServiceMapper::toDto)
                .collect(Collectors.toList());
    }
}