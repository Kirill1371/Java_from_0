package ru.senla.javacourse.tarasov.hotel.impl.service;

import ru.senla.javacourse.tarasov.hotel.api.dto.RoomDto;
import ru.senla.javacourse.tarasov.hotel.impl.service.RoomService;
import ru.senla.javacourse.tarasov.hotel.db.entity.Room;
import ru.senla.javacourse.tarasov.hotel.impl.mapper.RoomMapper;
import ru.senla.javacourse.tarasov.hotel.impl.repository.HotelRepositoryImpl;
import ru.senla.javacourse.tarasov.hotel.ioc.annotations.Component;
import ru.senla.javacourse.tarasov.hotel.ioc.annotations.Inject;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoomServiceImpl implements RoomService {

    @Inject
    private HotelRepositoryImpl hotelRepository;

    @Override
    public void addRoomToDatabase(RoomDto roomDto) {
        Room room = RoomMapper.toEntity(roomDto);
        hotelRepository.addRoomToDatabase(room);
    }

    @Override
    public void removeRoomFromDatabase(int roomNumber) {
        hotelRepository.removeRoomFromDatabase(roomNumber);
    }

    @Override
    public void setRoomStatus(int roomNumber, String status) {
        Room room = hotelRepository.getRoom(roomNumber);
        if (room != null) {
            room.setStatus(status);
            System.out.println("Room status updated: " + roomNumber + " to " + status);
        } else {
            System.out.println("Room not found: " + roomNumber);
        }
    }

    @Override
    public void setRoomPrice(int roomNumber, double price) {
        Room room = hotelRepository.getRoom(roomNumber);
        if (room != null) {
            room.setPrice(price);
            System.out.println("Room price updated: " + roomNumber + " to " + price);
        } else {
            System.out.println("Room not found: " + roomNumber);
        }
    }

    @Override
    public List<RoomDto> getAllRooms() {
        return RoomMapper.toDtoList(hotelRepository.getAllRooms());
    }

    @Override
    public List<RoomDto> getAvailableRooms() {
        return RoomMapper.toDtoList(hotelRepository.getAvailableRooms());
    }

    @Override
    public List<RoomDto> getRoomsSortedByPrice() {
        return hotelRepository.getAllRooms().stream()
                .sorted(Comparator.comparingDouble(Room::getPrice))
                .map(RoomMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomDto> getRoomsSortedByCapacity() {
        return hotelRepository.getAllRooms().stream()
                .sorted(Comparator.comparingInt(Room::getCapacity))
                .map(RoomMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomDto> getRoomsSortedByStars() {
        return hotelRepository.getAllRooms().stream()
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
        return (int) hotelRepository.getAllRooms().stream()
                .filter(room -> room.getStatus().equals("Available"))
                .count();
    }

    @Override
    public List<RoomDto> getRoomsAvailableByDate(Date date) {
        return hotelRepository.getAllRooms().stream()
                .filter(room -> room.getStatus().equals("Available") ||
                        room.getStays().stream().anyMatch(stay -> stay.getCheckOutDate().before(date)))
                .map(RoomMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public RoomDto getRoomDetails(int roomNumber) {
        Room room = hotelRepository.getRoomFromDatabase(roomNumber);
        return RoomMapper.toDto(room);
    }
}