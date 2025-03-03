package ru.senla.javacourse.tarasov.hotel.impl.mapper;

import java.util.List;
import java.util.stream.Collectors;
import ru.senla.javacourse.tarasov.hotel.api.dto.StayDto;
import ru.senla.javacourse.tarasov.hotel.db.entity.Stay;

public class StayMapper {

    // Преобразование Stay (Entity) в StayDto
    public static StayDto toDto(Stay stay) {
        return StayDto.builder()
                .room(RoomMapper.toDto(stay.getRoom()))
                .guest(GuestMapper.toDto(stay.getGuest()))
                .checkInDate(stay.getCheckInDate())
                .checkOutDate(stay.getCheckOutDate())
                .build();
    }

    // Преобразование StayDto в Stay (Entity)
    public static Stay toEntity(StayDto stayDto) {
        return Stay.builder()
                .room(RoomMapper.toEntity(stayDto.getRoom()))
                .guest(GuestMapper.toEntity(stayDto.getGuest()))
                .checkInDate(stayDto.getCheckInDate())
                .checkOutDate(stayDto.getCheckOutDate())
                .build();
    }

    // Преобразование списка Stay в список StayDto
    public static List<StayDto> toDtoList(List<Stay> stays) {
        return stays.stream()
                .map(StayMapper::toDto)
                .collect(Collectors.toList());
    }

    // Преобразование списка StayDto в список Stay
    public static List<Stay> toEntityList(List<StayDto> stayDtos) {
        return stayDtos.stream()
                .map(StayMapper::toEntity)
                .collect(Collectors.toList());
    }
}