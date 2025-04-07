package ru.senla.javacourse.tarasov.hotel.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Data
@Getter
public class AuthResponseDto {
    private String token;
}