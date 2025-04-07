package ru.senla.javacourse.tarasov.hotel.impl.service.impl;


import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.senla.javacourse.tarasov.hotel.api.dto.GuestDto;
import ru.senla.javacourse.tarasov.hotel.db.entity.Guest;
import ru.senla.javacourse.tarasov.hotel.db.entity.Stay;
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
        // Загружаем гостей
        List<Guest> guests = guestRepository.getAllGuests();

        // Преобразуем в DTO
        return guests.stream()
                .map(GuestMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<GuestDto> getGuestsSortedByName() {
        // Загружаем гостей с их stays и services
        List<Guest> guests = guestRepository.getAllGuestsWithStaysAndServices();

        // Сортируем гостей по имени
        guests.sort(Comparator.comparing(Guest::getName));

        // Преобразуем в DTO
        return guests.stream()
                .map(GuestMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<GuestDto> getGuestsSortedByCheckOutDate() {
        // Загружаем гостей с их stays
        List<Guest> guests = guestRepository.getAllGuestsWithStays();

        // Сортируем гостей по дате выезда
        guests.sort((g1, g2) -> {
            // Используем Stream для поиска последнего элемента
            Date d1 = g1.getStays().stream()
                    .reduce((first, second) -> second) // Берем последний элемент
                    .map(Stay::getCheckOutDate)
                    .orElseThrow(() -> new RuntimeException("No stays found for guest")); // Обработка случая, если stays пуст

            Date d2 = g2.getStays().stream()
                    .reduce((first, second) -> second) // Берем последний элемент
                    .map(Stay::getCheckOutDate)
                    .orElseThrow(() -> new RuntimeException("No stays found for guest")); // Обработка случая, если stays пуст

            return d1.compareTo(d2);
        });

        // Преобразуем в DTO
        return guests.stream()
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



//@Service
//public class GuestServiceImpl implements GuestService {
//
//    private final GuestRepository guestRepository;
//
//    @Autowired
//    public GuestServiceImpl(GuestRepository guestRepository) {
//        this.guestRepository = guestRepository;
//    }
//
//    @Override
//    @Transactional
//    public List<GuestDto> getAllGuests() {
//        List<Guest> guests = guestRepository.getAllGuests();
//        guests.forEach(guest -> {
//            Hibernate.initialize(guest.getStays());
//            Hibernate.initialize(guest.getServices());
//        });
//        return GuestMapper.toDtoList(guests);
//    }
//
//
//    @Override
//    @Transactional
//    public List<GuestDto> getGuestsSortedByName() { // исправлено
//        List<Guest> guests = guestRepository.getAllGuests()
//                .stream()
//                .sorted(Comparator.comparing(Guest::getName))
//                .collect(Collectors.toList());
//
//        guests.forEach(guest -> {
//            Hibernate.initialize(guest.getStays());
//            Hibernate.initialize(guest.getServices());
//        });
//
//        return GuestMapper.toDtoList(guests);
//    }
//
//
//    @Override
//    public List<GuestDto> getGuestsSortedByCheckOutDate() {
//        return guestRepository.getAllGuests().stream()
//                .sorted((g1, g2) -> {
//                    Date d1 = g1.getStays().get(g1.getStays().size() - 1).getCheckOutDate();
//                    Date d2 = g2.getStays().get(g2.getStays().size() - 1).getCheckOutDate();
//                    return d1.compareTo(d2);
//                })
//                .map(GuestMapper::toDto)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public int getTotalGuests() {
//        return guestRepository.getAllGuests().size();
//    }
//
//    @Override
//    public double getTotalPaymentForGuest(String guestName) {
//        Guest guest = guestRepository.getGuest(guestName);
//        if (guest == null) {
//            return 0;
//        }
//        return guest.getStays().stream()
//                .mapToDouble(stay -> stay.getRoom().getPrice() * (stay.getCheckOutDate().getTime() - stay.getCheckInDate().getTime()) / (1000 * 60 * 60 * 24))
//                .sum();
//    }
//}
