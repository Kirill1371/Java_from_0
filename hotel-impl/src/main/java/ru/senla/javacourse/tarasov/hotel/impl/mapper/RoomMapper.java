package ru.senla.javacourse.tarasov.hotel.impl.mapper;

import java.util.List;
import java.util.stream.Collectors;
import ru.senla.javacourse.tarasov.hotel.api.dto.RoomDto;
import ru.senla.javacourse.tarasov.hotel.db.entity.Room;

public class RoomMapper {

    public static RoomDto toDto(Room room) {
        return RoomDto.builder()
                .number(room.getNumber())
                .status(room.getStatus())
                .price(room.getPrice())
                .capacity(room.getCapacity())
                .stars(room.getStars())
                .build();
    }

    public static Room toEntity(RoomDto roomDto) {
        return Room.builder()
                .number(roomDto.getNumber())
                .status(roomDto.getStatus())
                .price(roomDto.getPrice())
                .capacity(roomDto.getCapacity())
                .stars(roomDto.getStars())
                .build();
    }

    public static List<RoomDto> toDtoList(List<Room> rooms) {
        return rooms.stream()
                .map(RoomMapper::toDto)
                .collect(Collectors.toList());
    }

    public static List<Room> toEntityList(List<RoomDto> roomDtos) {
        return roomDtos.stream()
                .map(RoomMapper::toEntity)
                .collect(Collectors.toList());
    }
}