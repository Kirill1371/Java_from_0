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
import ru.senla.javacourse.tarasov.hotel.impl.repository.GuestRepository;
import ru.senla.javacourse.tarasov.hotel.impl.repository.RoomRepository;
import ru.senla.javacourse.tarasov.hotel.impl.repository.StayRepository;
import ru.senla.javacourse.tarasov.hotel.impl.service.impl.CheckServiceImpl;

import java.util.Date;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CheckServiceImplTest {

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private GuestRepository guestRepository;

    @Mock
    private StayRepository stayRepository;

    @InjectMocks
    private CheckServiceImpl checkService;

    private Room testRoom;
    private GuestDto testGuestDto;
    private Guest testGuest;
    private Date checkInDate;
    private Date checkOutDate;

    @BeforeEach
    void setUp() {
        testRoom = new Room();
        testRoom.setNumber(101);
        testRoom.setStatus("Available");

        testGuestDto = new GuestDto();
        testGuestDto.setName("John Doe");

        testGuest = new Guest();
        testGuest.setName("John Doe");

        checkInDate = new Date();
        checkOutDate = new Date(checkInDate.getTime() + 86400000); // +1 день
    }

    @Test
    void checkIn_ShouldCheckInGuest_WhenRoomIsAvailable() {
        when(roomRepository.getRoom(101)).thenReturn(testRoom);
        when(guestRepository.getGuest("John Doe")).thenReturn(null);

        checkService.checkIn(101, testGuestDto, checkInDate, checkOutDate);

        verify(roomRepository).updateRoomStatus(101, "Occupied");
        verify(guestRepository).addGuest(any(Guest.class));
        verify(stayRepository).addStay(any(Stay.class));
    }

    @Test
    void checkIn_ShouldUseExistingGuest_WhenGuestExists() {
        when(roomRepository.getRoom(101)).thenReturn(testRoom);
        when(guestRepository.getGuest("John Doe")).thenReturn(testGuest);

        checkService.checkIn(101, testGuestDto, checkInDate, checkOutDate);

        verify(guestRepository, never()).addGuest(any());
        verify(stayRepository).addStay(argThat(stay -> stay.getGuest().equals(testGuest)));
    }

    @Test
    void checkIn_ShouldNotCheckIn_WhenRoomNotAvailable() {
        testRoom.setStatus("Occupied");
        when(roomRepository.getRoom(101)).thenReturn(testRoom);

        checkService.checkIn(101, testGuestDto, checkInDate, checkOutDate);

        verify(roomRepository, never()).updateRoomStatus(anyInt(), anyString());
        verify(guestRepository, never()).addGuest(any());
        verify(stayRepository, never()).addStay(any());
    }

    @Test
    void checkIn_ShouldNotCheckIn_WhenRoomNotFound() {
        when(roomRepository.getRoom(101)).thenReturn(null);

        checkService.checkIn(101, testGuestDto, checkInDate, checkOutDate);

        verify(roomRepository, never()).updateRoomStatus(anyInt(), anyString());
        verify(guestRepository, never()).addGuest(any());
        verify(stayRepository, never()).addStay(any());
    }

    @Test
    void checkOut_ShouldCheckOutGuest_WhenRoomExists() {
        when(roomRepository.getRoom(101)).thenReturn(testRoom);

        checkService.checkOut(101);

        verify(roomRepository).setStatusAv(101);
    }

    @Test
    void checkOut_ShouldNotFail_WhenRoomNotFound() {
        when(roomRepository.getRoom(101)).thenReturn(null);

        checkService.checkOut(101);

        verify(roomRepository, never()).setStatusAv(anyInt());
    }
}