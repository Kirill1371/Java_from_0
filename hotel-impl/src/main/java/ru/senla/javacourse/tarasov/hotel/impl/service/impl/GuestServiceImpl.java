package ru.senla.javacourse.tarasov.hotel.impl.service.impl;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.senla.javacourse.tarasov.hotel.api.dto.GuestDto;
import ru.senla.javacourse.tarasov.hotel.db.entity.Guest;
import ru.senla.javacourse.tarasov.hotel.impl.mapper.GuestMapper;
//import ru.senla.javacourse.tarasov.hotel.impl.repository.HotelRepository;
import ru.senla.javacourse.tarasov.hotel.impl.repository.GuestRepository;
import ru.senla.javacourse.tarasov.hotel.impl.service.GuestService;
import ru.senla.javacourse.tarasov.hotel.ioc.annotations.Component;
import ru.senla.javacourse.tarasov.hotel.ioc.annotations.Inject;

@Service
public class GuestServiceImpl implements GuestService {

    private GuestRepository guestRepository;

    @Autowired // Внедрение зависимости через конструктор
    public GuestServiceImpl(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @Override
    public List<GuestDto> getAllGuests() {
        return GuestMapper.toDtoList(guestRepository.getAllGuests());
    }

    @Override
    public List<GuestDto> getGuestsSortedByName() {
        return guestRepository.getAllGuests().stream()
                .sorted((g1, g2) -> g1.getName().compareTo(g2.getName()))
                .map(GuestMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<GuestDto> getGuestsSortedByCheckOutDate() {
        return guestRepository.getAllGuests().stream()
                .sorted((g1, g2) -> {
                    Date d1 = g1.getStays().get(g1.getStays().size() - 1).getCheckOutDate();
                    Date d2 = g2.getStays().get(g2.getStays().size() - 1).getCheckOutDate();
                    return d1.compareTo(d2);
                })
                .map(GuestMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public int getTotalGuests() {
        return guestRepository.getAllGuests().size();
    }

    @Override
    public double getTotalPaymentForGuest(String guestName) {
        Guest guest = guestRepository.getGuest(guestName);
        if (guest == null) {
            return 0;
        }
        return guest.getStays().stream()
                .mapToDouble(stay -> stay.getRoom().getPrice() * (stay.getCheckOutDate().getTime() - stay.getCheckInDate().getTime()) / (1000 * 60 * 60 * 24))
                .sum();
    }
}