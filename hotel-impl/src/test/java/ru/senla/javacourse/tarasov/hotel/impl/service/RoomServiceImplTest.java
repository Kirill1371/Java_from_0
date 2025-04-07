package ru.senla.javacourse.tarasov.hotel.impl.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.senla.javacourse.tarasov.hotel.api.dto.RoomDto;
import ru.senla.javacourse.tarasov.hotel.db.entity.Room;
import ru.senla.javacourse.tarasov.hotel.impl.mapper.RoomMapper;
import ru.senla.javacourse.tarasov.hotel.impl.repository.RoomRepository;
import ru.senla.javacourse.tarasov.hotel.impl.service.impl.RoomServiceImpl;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoomServiceImplTest {

    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private RoomServiceImpl roomService;

    private Room testRoom;
    private RoomDto testRoomDto;

    @BeforeEach
    void setUp() {
        testRoom = new Room();
        testRoom.setNumber(101);
        testRoom.setPrice(100.0);
        testRoom.setCapacity(2);
        testRoom.setStars(3);
        testRoom.setStatus("Available");

        testRoomDto = new RoomDto();
        testRoomDto.setNumber(101);
        testRoomDto.setPrice(100.0);
        testRoomDto.setCapacity(2);
        testRoomDto.setStars(3);
        testRoomDto.setStatus("Available");
    }

    @Test
    void addRoom_ShouldCallRepositoryAddRoom() {
        doNothing().when(roomRepository).addRoom(any(Room.class));

        roomService.addRoom(testRoomDto);

        verify(roomRepository, times(1)).addRoom(any(Room.class));
    }

    @Test
    void removeRoom_ShouldCallRepositoryRemoveRoom() {
        doNothing().when(roomRepository).removeRoom(anyInt());

        roomService.removeRoom(101);

        verify(roomRepository, times(1)).removeRoom(101);
    }

    @Test
    void setRoomStatus_WhenRoomExists_ShouldUpdateStatus() {
        when(roomRepository.getRoom(101)).thenReturn(testRoom);

        roomService.setRoomStatus(101, "Occupied");

        assertEquals("Occupied", testRoom.getStatus());
        verify(roomRepository, times(1)).getRoom(101);
    }

    @Test
    void setRoomStatus_WhenRoomNotExists_ShouldNotThrowException() {
        when(roomRepository.getRoom(101)).thenReturn(null);

        assertDoesNotThrow(() -> roomService.setRoomStatus(101, "Occupied"));
        verify(roomRepository, times(1)).getRoom(101);
    }

    @Test
    void setRoomPrice_WhenRoomExists_ShouldUpdatePrice() {
        when(roomRepository.getRoom(101)).thenReturn(testRoom);

        roomService.setRoomPrice(101, 150.0);

        assertEquals(150.0, testRoom.getPrice());
        verify(roomRepository, times(1)).getRoom(101);
    }

    @Test
    void setRoomPrice_WhenRoomNotExists_ShouldNotThrowException() {
        when(roomRepository.getRoom(101)).thenReturn(null);

        assertDoesNotThrow(() -> roomService.setRoomPrice(101, 150.0));
        verify(roomRepository, times(1)).getRoom(101);
    }

    @Test
    void getAllRooms_ShouldReturnAllRooms() {
        List<Room> rooms = Collections.singletonList(testRoom);
        when(roomRepository.getAllRooms()).thenReturn(rooms);

        List<RoomDto> result = roomService.getAllRooms();

        assertEquals(1, result.size());
        assertEquals(101, result.get(0).getNumber());
        verify(roomRepository, times(1)).getAllRooms();
    }

    @Test
    void getAvailableRooms_ShouldReturnOnlyAvailableRooms() {
        Room occupiedRoom = new Room();
        occupiedRoom.setNumber(102);
        occupiedRoom.setStatus("Occupied");

        when(roomRepository.getAvailableRooms()).thenReturn(Collections.singletonList(testRoom));

        List<RoomDto> result = roomService.getAvailableRooms();

        assertEquals(1, result.size());
        assertEquals("Available", result.get(0).getStatus());
        verify(roomRepository, times(1)).getAvailableRooms();
    }

    @Test
    void getRoomsSortedByPrice_ShouldReturnSortedRooms() {
        Room cheapRoom = new Room();
        cheapRoom.setNumber(101);
        cheapRoom.setPrice(100.0);

        Room expensiveRoom = new Room();
        expensiveRoom.setNumber(102);
        expensiveRoom.setPrice(200.0);

        when(roomRepository.getAllRooms()).thenReturn(Arrays.asList(expensiveRoom, cheapRoom));
        List<RoomDto> result = roomService.getRoomsSortedByPrice();

        assertEquals(2, result.size());
        assertEquals(100.0, result.get(0).getPrice());
        assertEquals(200.0, result.get(1).getPrice());
        verify(roomRepository, times(1)).getAllRooms();
    }

    @Test
    void getRoomsSortedByCapacity_ShouldReturnSortedRooms() {
        Room smallRoom = new Room();
        smallRoom.setNumber(101);
        smallRoom.setCapacity(1);

        Room largeRoom = new Room();
        largeRoom.setNumber(102);
        largeRoom.setCapacity(3);

        when(roomRepository.getAllRooms()).thenReturn(Arrays.asList(largeRoom, smallRoom));

        List<RoomDto> result = roomService.getRoomsSortedByCapacity();

        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getCapacity());
        assertEquals(3, result.get(1).getCapacity());
        verify(roomRepository, times(1)).getAllRooms();
    }

    @Test
    void getRoomsSortedByStars_ShouldReturnSortedRooms() {
        Room lowStarRoom = new Room();
        lowStarRoom.setNumber(101);
        lowStarRoom.setStars(2);

        Room highStarRoom = new Room();
        highStarRoom.setNumber(102);
        highStarRoom.setStars(4);

        when(roomRepository.getAllRooms()).thenReturn(Arrays.asList(highStarRoom, lowStarRoom));

        List<RoomDto> result = roomService.getRoomsSortedByStars();

        assertEquals(2, result.size());
        assertEquals(2, result.get(0).getStars());
        assertEquals(4, result.get(1).getStars());
        verify(roomRepository, times(1)).getAllRooms();
    }

    @Test
    void getAvailableRoomsSortedByPrice_ShouldReturnSortedAvailableRooms() {
        RoomDto cheapRoom = new RoomDto();
        cheapRoom.setNumber(101);
        cheapRoom.setPrice(100.0);
        cheapRoom.setStatus("Available");

        RoomDto expensiveRoom = new RoomDto();
        expensiveRoom.setNumber(102);
        expensiveRoom.setPrice(200.0);
        expensiveRoom.setStatus("Available");

        when(roomRepository.getAvailableRooms()).thenReturn(Arrays.asList(
                RoomMapper.toEntity(expensiveRoom),
                RoomMapper.toEntity(cheapRoom)
        ));

        List<RoomDto> result = roomService.getAvailableRoomsSortedByPrice();

        assertEquals(2, result.size());
        assertEquals(100.0, result.get(0).getPrice());
        assertEquals(200.0, result.get(1).getPrice());
        verify(roomRepository, times(1)).getAvailableRooms();
    }

    @Test
    void getTotalAvailableRooms_ShouldReturnCorrectCount() {
        Room availableRoom1 = new Room();
        availableRoom1.setStatus("Available");
        Room availableRoom2 = new Room();
        availableRoom2.setStatus("Available");
        Room occupiedRoom = new Room();
        occupiedRoom.setStatus("Occupied");

        when(roomRepository.getAllRooms()).thenReturn(Arrays.asList(availableRoom1, availableRoom2, occupiedRoom));
        int result = roomService.getTotalAvailableRooms();
        assertEquals(2, result);
        verify(roomRepository, times(1)).getAllRooms();
    }

    @Test
    void getRoomDetails_ShouldReturnRoomDetails() {
        when(roomRepository.getRoom(101)).thenReturn(testRoom);
        RoomDto result = roomService.getRoomDetails(101);

        assertNotNull(result);
        assertEquals(101, result.getNumber());
        verify(roomRepository, times(1)).getRoom(101);
    }

    @Test
    void getRoomDetails_WhenRoomNotExists_ShouldReturnNull() {
        when(roomRepository.getRoom(101)).thenReturn(null);

        RoomDto result = roomService.getRoomDetails(101);

        assertNull(result);
        verify(roomRepository, times(1)).getRoom(101);
    }

    @Test
    void getRoomsAvailableByDate_ShouldReturnAvailableRooms() {
        // Arrange
        Date testDate = new Date();
        when(roomRepository.getAllRooms()).thenReturn(Collections.singletonList(testRoom));
        List<RoomDto> result = roomService.getRoomsAvailableByDate(testDate);

        assertEquals(1, result.size());
        assertEquals(101, result.get(0).getNumber());
        verify(roomRepository, times(1)).getAllRooms();
    }
}