package ru.senla.javacourse.tarasov.hotel.impl.mapper;

import java.util.List;
import java.util.stream.Collectors;
import ru.senla.javacourse.tarasov.hotel.api.dto.GuestDto;
import ru.senla.javacourse.tarasov.hotel.db.entity.Guest;

public class GuestMapper {

    public static GuestDto toDto(Guest guest) {
        return GuestDto.builder()
                .name(guest.getName())
                .stays(guest.getStays() != null ?
                        guest.getStays().stream()
                                .map(StayMapper::toDto)
                                .collect(Collectors.toList()) : null)
                .services(guest.getServices() != null ?
                        guest.getServices().stream()
                                .map(ServiceMapper::toDto)
                                .collect(Collectors.toList()) : null)
                .build();
    }

    // Преобразование GuestDto в Guest (Entity)
    public static Guest toEntity(GuestDto guestDto) {
        return Guest.builder()
                .name(guestDto.getName())
                .stays(guestDto.getStays() != null ?
                        guestDto.getStays().stream()
                                .map(StayMapper::toEntity)
                                .collect(Collectors.toSet()) : null)
                .services(guestDto.getServices() != null ?
                        guestDto.getServices().stream()
                                .map(ServiceMapper::toEntity)
                                .collect(Collectors.toSet()) : null)
                .build();
    }

    // Преобразование списка Guest в список GuestDto
    public static List<GuestDto> toDtoList(List<Guest> guests) {
        return guests.stream()
                .map(GuestMapper::toDto)
                .collect(Collectors.toList());
    }

    // Преобразование списка GuestDto в список Guest
    public static List<Guest> toEntityList(List<GuestDto> guestDtos) {
        return guestDtos.stream()
                .map(GuestMapper::toEntity)
                .collect(Collectors.toList());
    }
}
