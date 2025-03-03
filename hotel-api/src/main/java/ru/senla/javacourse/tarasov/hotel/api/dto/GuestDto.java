package ru.senla.javacourse.tarasov.hotel.api.dto;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class GuestDto {
    private String name;

    private UUID id;

    private List<StayDto> stays;

    private List<ServiceDto> services;
}
