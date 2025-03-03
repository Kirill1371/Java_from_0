package ru.senla.javacourse.tarasov.hotel.impl.service;


import ru.senla.javacourse.tarasov.hotel.api.dto.GuestDto;
import ru.senla.javacourse.tarasov.hotel.db.entity.Guest;
import ru.senla.javacourse.tarasov.hotel.impl.mapper.RoomMapper;
import ru.senla.javacourse.tarasov.hotel.impl.service.GuestService;
import ru.senla.javacourse.tarasov.hotel.impl.mapper.GuestMapper;
import ru.senla.javacourse.tarasov.hotel.impl.repository.HotelRepositoryImpl;
import ru.senla.javacourse.tarasov.hotel.ioc.annotations.Component;
import ru.senla.javacourse.tarasov.hotel.ioc.annotations.Inject;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GuestServiceImpl implements GuestService {

    @Inject
    private HotelRepositoryImpl hotelRepository;

    @Override
    public List<GuestDto> getAllGuests() {
        return GuestMapper.toDtoList(hotelRepository.getAllGuests());
    }

    @Override
    public List<GuestDto> getGuestsSortedByName() {
        return hotelRepository.getAllGuests().stream()
                .sorted((g1, g2) -> g1.getName().compareTo(g2.getName()))
                .map(GuestMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<GuestDto> getGuestsSortedByCheckOutDate() {
        return hotelRepository.getAllGuests().stream()
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
        return hotelRepository.getAllGuests().size();
    }

    @Override
    public double getTotalPaymentForGuest(String guestName) {
        Guest guest = hotelRepository.getGuest(guestName);
        if (guest == null) {
            return 0;
        }
        return guest.getStays().stream()
                .mapToDouble(stay -> stay.getRoom().getPrice() * (stay.getCheckOutDate().getTime() - stay.getCheckInDate().getTime()) / (1000 * 60 * 60 * 24))
                .sum();
    }
}