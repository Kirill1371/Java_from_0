package ru.senla.javacourse.tarasov.hotel.api.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class HotelDto {
    private List<RoomDto> rooms;
    private List<ServiceDto> services;
}
