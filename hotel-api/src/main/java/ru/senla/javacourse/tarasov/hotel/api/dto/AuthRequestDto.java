package ru.senla.javacourse.tarasov.hotel.api.dto;
import lombok.Data;

@Data
public class AuthRequestDto {
    private String username;
    private String password;
}