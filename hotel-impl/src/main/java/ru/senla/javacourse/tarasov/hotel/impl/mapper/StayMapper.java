package ru.senla.javacourse.tarasov.hotel.impl.mapper;

import java.util.List;
import java.util.stream.Collectors;
import ru.senla.javacourse.tarasov.hotel.api.dto.StayDto;
import ru.senla.javacourse.tarasov.hotel.db.entity.Guest;
import ru.senla.javacourse.tarasov.hotel.db.entity.Room;
import ru.senla.javacourse.tarasov.hotel.db.entity.Stay;

public class StayMapper {

    public static StayDto toDto(Stay stay) {
        return StayDto.builder()
                .roomId(stay.getRoom().getId()) // Используем только ID комнаты
                .guestId(stay.getGuest().getId()) // Используем только ID гостя
                .checkInDate(stay.getCheckInDate())
                .checkOutDate(stay.getCheckOutDate())
                .build();
    }

    public static Stay toEntity(StayDto stayDto) {
        return Stay.builder()
                .room(Room.builder().id(stayDto.getRoomId()).build()) // Создаем Room только с ID
                .guest(Guest.builder().id(stayDto.getGuestId()).build()) // Создаем Guest только с ID
                .checkInDate(stayDto.getCheckInDate())
                .checkOutDate(stayDto.getCheckOutDate())
                .build();
    }

    public static List<StayDto> toDtoList(List<Stay> stays) {
        return stays.stream()
                .map(StayMapper::toDto)
                .collect(Collectors.toList());
    }

    public static List<Stay> toEntityList(List<StayDto> stayDtos) {
        return stayDtos.stream()
                .map(StayMapper::toEntity)
                .collect(Collectors.toList());
    }
}