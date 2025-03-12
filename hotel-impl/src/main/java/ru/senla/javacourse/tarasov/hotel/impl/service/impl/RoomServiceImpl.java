package ru.senla.javacourse.tarasov.hotel.impl.service.impl;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.senla.javacourse.tarasov.hotel.api.dto.RoomDto;
import ru.senla.javacourse.tarasov.hotel.db.entity.Room;
import ru.senla.javacourse.tarasov.hotel.impl.mapper.RoomMapper;
//import ru.senla.javacourse.tarasov.hotel.impl.repository.HotelRepository;
import ru.senla.javacourse.tarasov.hotel.impl.repository.RoomRepository;
import ru.senla.javacourse.tarasov.hotel.impl.service.RoomService;
import ru.senla.javacourse.tarasov.hotel.ioc.annotations.Component;
import ru.senla.javacourse.tarasov.hotel.ioc.annotations.Inject;

@Service
public class RoomServiceImpl implements RoomService {

    private RoomRepository roomRepository;

    @Value("${room.default.price}")
    private double defaultPrice;

    @Value("${room.default.capacity}")
    private int defaultCapacity;


    @Autowired // Внедрение зависимости через конструктор
    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public void addRoom(RoomDto roomDto) {
        Room room = RoomMapper.toEntity(roomDto);
        roomRepository.addRoom(room);
    }

    @Override
    public void removeRoom(int roomNumber) {
        roomRepository.removeRoom(roomNumber);
    }

    @Override
    public void setRoomStatus(int roomNumber, String status) {
        Room room = roomRepository.getRoom(roomNumber);
        if (room != null) {
            room.setStatus(status);
            System.out.println("Room status updated: " + roomNumber + " to " + status);
        } else {
            System.out.println("Room not found: " + roomNumber);
        }
    }

    @Override
    public void setRoomPrice(int roomNumber, double price) {
        Room room = roomRepository.getRoom(roomNumber);
        if (room != null) {
            room.setPrice(price);
            System.out.println("Room price updated: " + roomNumber + " to " + price);
        } else {
            System.out.println("Room not found: " + roomNumber);
        }
    }

    @Override
    public List<RoomDto> getAllRooms() {
        return RoomMapper.toDtoList(roomRepository.getAllRooms());
    }

    @Override
    public List<RoomDto> getAvailableRooms() {
        return RoomMapper.toDtoList(roomRepository.getAvailableRooms());
    }

    @Override
    public List<RoomDto> getRoomsSortedByPrice() {
        return roomRepository.getAllRooms().stream()
                .sorted(Comparator.comparingDouble(Room::getPrice))
                .map(RoomMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomDto> getRoomsSortedByCapacity() {
        return roomRepository.getAllRooms().stream()
                .sorted(Comparator.comparingInt(Room::getCapacity))
                .map(RoomMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomDto> getRoomsSortedByStars() {
        return roomRepository.getAllRooms().stream()
                .sorted(Comparator.comparingInt(Room::getStars))
                .map(RoomMapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public List<RoomDto> getAvailableRoomsSortedByPrice() {
        return getAvailableRooms().
                stream()
                .sorted((r1, r2) -> Double.compare(r1.getPrice(), r2.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomDto> getAvailableRoomsSortedByCapacity() {
        return getAvailableRooms().stream()
                .sorted((r1, r2) -> Integer.compare(r1.getCapacity(), r2.getCapacity()))
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomDto> getAvailableRoomsSortedByStars() {
        return getAvailableRooms().stream()
                .sorted((r1, r2) -> Integer.compare(r1.getStars(), r2.getStars()))
                .collect(Collectors.toList());
    }

    @Override
    public int getTotalAvailableRooms() {
        return (int) roomRepository.getAllRooms().stream()
                .filter(room -> room.getStatus().equals("Available"))
                .count();
    }

    @Override
    public List<RoomDto> getRoomsAvailableByDate(Date date) {
        return roomRepository.getAllRooms().stream()
                .filter(room -> room.getStatus().equals("Available") ||
                        room.getStays().stream().anyMatch(stay -> stay.getCheckOutDate().before(date)))
                .map(RoomMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public RoomDto getRoomDetails(int roomNumber) {
        Room room = roomRepository.getRoom(roomNumber);
        return RoomMapper.toDto(room);
    }
}