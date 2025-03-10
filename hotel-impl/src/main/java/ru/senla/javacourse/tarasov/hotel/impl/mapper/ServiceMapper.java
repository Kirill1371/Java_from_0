package ru.senla.javacourse.tarasov.hotel.impl.mapper;

import java.util.List;
import java.util.stream.Collectors;
import ru.senla.javacourse.tarasov.hotel.api.dto.ServiceDto;
import ru.senla.javacourse.tarasov.hotel.db.entity.Service;

public class ServiceMapper {

    // Преобразование Service (Entity) в ServiceDto
    public static ServiceDto toDto(Service service) {
        return ServiceDto.builder()
                .name(service.getName())
                .price(service.getPrice())
                .category(service.getCategory())
                .date(service.getDate())
                .guest(GuestMapper.toDto(service.getGuest()))
                .build();
    }

    // Преобразование ServiceDto в Service (Entity)
    public static Service toEntity(ServiceDto serviceDto) {
        return Service.builder()
                .name(serviceDto.getName())
                .price(serviceDto.getPrice())
                .category(serviceDto.getCategory())
                .date(serviceDto.getDate())
                .guest(GuestMapper.toEntity(serviceDto.getGuest()))
                .build();
    }

    // Преобразование списка Service в список ServiceDto
    public static List<ServiceDto> toDtoList(List<Service> services) {
        return services.stream()
                .map(ServiceMapper::toDto)
                .collect(Collectors.toList());
    }

    // Преобразование списка ServiceDto в список Service
    public static List<Service> toEntityList(List<ServiceDto> serviceDtos) {
        return serviceDtos.stream()
                .map(ServiceMapper::toEntity)
                .collect(Collectors.toList());
    }
}