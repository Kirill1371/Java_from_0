package ru.senla.javacourse.tarasov.hotel.impl.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.senla.javacourse.tarasov.hotel.api.dto.GuestDto;
import ru.senla.javacourse.tarasov.hotel.db.entity.Guest;
import ru.senla.javacourse.tarasov.hotel.db.entity.Room;
import ru.senla.javacourse.tarasov.hotel.db.entity.Stay;
import ru.senla.javacourse.tarasov.hotel.impl.mapper.GuestMapper;
import ru.senla.javacourse.tarasov.hotel.impl.repository.GuestRepository;
import ru.senla.javacourse.tarasov.hotel.impl.service.impl.GuestServiceImpl;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GuestServiceImplTest {

    @Mock
    private GuestRepository guestRepository;

    @InjectMocks
    private GuestServiceImpl guestService;

    private Guest guest1;
    private Guest guest2;
    private Stay stay1;
    private Stay stay2;
    private Room room;

    @BeforeEach
    void setUp() {
        room = new Room();
        room.setPrice(100.0);

        stay1 = new Stay();
        stay1.setCheckInDate(new Date(1640995200000L)); // 01.01.2022
        stay1.setCheckOutDate(new Date(1641081600000L)); // 02.01.2022
        stay1.setRoom(room);

        stay2 = new Stay();
        stay2.setCheckInDate(new Date(1641168000000L)); // 03.01.2022
        stay2.setCheckOutDate(new Date(1641254400000L)); // 04.01.2022
        stay2.setRoom(room);

        guest1 = new Guest();
        guest1.setName("John Doe");
        guest1.setStays(new HashSet<>(Collections.singletonList(stay1)));


        guest2 = new Guest();
        guest2.setName("Alice Smith");
        guest1.setStays(new HashSet<>(Collections.singletonList(stay2)));

    }

    @Test
    void getAllGuests_ShouldReturnAllGuests() {
        when(guestRepository.getAllGuests()).thenReturn(Arrays.asList(guest1, guest2));
        List<GuestDto> result = guestService.getAllGuests();

        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getName());
        assertEquals("Alice Smith", result.get(1).getName());
        verify(guestRepository, times(1)).getAllGuests();
    }

    @Test
    void getGuestsSortedByName_ShouldReturnSortedGuests() {
        when(guestRepository.getAllGuestsWithStaysAndServices())
                .thenReturn(Arrays.asList(guest2, guest1)); // Исходно не отсортировано

        List<GuestDto> result = guestService.getGuestsSortedByName();

        assertEquals(2, result.size());
        assertEquals("Alice Smith", result.get(0).getName()); // Первая после сортировки
        assertEquals("John Doe", result.get(1).getName());    // Вторая после сортировки
        verify(guestRepository, times(1)).getAllGuestsWithStaysAndServices();
    }

    @Test
    void getGuestsSortedByCheckOutDate_ShouldReturnSortedGuests() {
        when(guestRepository.getAllGuestsWithStays())
                .thenReturn(Arrays.asList(guest1, guest2)); // guest1 имеет более раннюю дату выезда

        List<GuestDto> result = guestService.getGuestsSortedByCheckOutDate();

        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getName()); // Первый по дате выезда
        assertEquals("Alice Smith", result.get(1).getName());
        verify(guestRepository, times(1)).getAllGuestsWithStays();
    }

    @Test
    void getTotalGuests_ShouldReturnCorrectCount() {
        when(guestRepository.getAllGuests()).thenReturn(Arrays.asList(guest1, guest2));

        int result = guestService.getTotalGuests();

        assertEquals(2, result);
        verify(guestRepository, times(1)).getAllGuests();
    }

    @Test
    void getTotalPaymentForGuest_ShouldReturnCorrectSum() {
        when(guestRepository.getGuest("John Doe")).thenReturn(guest1);

        double result = guestService.getTotalPaymentForGuest("John Doe");

        assertEquals(100.0, result, 0.001); // 1 день * 100 руб/день
        verify(guestRepository, times(1)).getGuest("John Doe");
    }

    @Test
    void getTotalPaymentForGuest_ShouldReturnZeroForUnknownGuest() {
        when(guestRepository.getGuest("Unknown")).thenReturn(null);

        double result = guestService.getTotalPaymentForGuest("Unknown");

        assertEquals(0.0, result, 0.001);
        verify(guestRepository, times(1)).getGuest("Unknown");
    }

    @Test
    void getTotalPaymentForGuest_ShouldHandleMultipleStays() {
        guest1.setStays(new HashSet<>(Arrays.asList(stay1, stay2))); // 2 stays по 1 дню каждый
        when(guestRepository.getGuest("John Doe")).thenReturn(guest1);

        double result = guestService.getTotalPaymentForGuest("John Doe");

        assertEquals(200.0, result, 0.001); // 2 дня * 100 руб/день
    }
}