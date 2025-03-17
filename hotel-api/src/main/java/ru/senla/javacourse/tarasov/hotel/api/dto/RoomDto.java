package ru.senla.javacourse.tarasov.hotel.api.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto {
    private int number;

    private String status;

    private double price;

    private int capacity;

    private int stars;

    //private List<StayDto> stays = new ArrayList<>();

    public RoomDto(int number, double price, int capacity, int stars) {
        this.number = number;
        this.price = price;
        this.capacity = capacity;
        this.stars = stars;
    }
}
