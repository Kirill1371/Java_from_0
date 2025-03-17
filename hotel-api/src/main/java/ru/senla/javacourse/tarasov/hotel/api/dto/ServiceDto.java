package ru.senla.javacourse.tarasov.hotel.api.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDto {
    private String name;

    private double price;

    private String category;

    private Date date;

    //private GuestDto guest;

    public ServiceDto(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }
}
